package com.natvalentine.player.commands.usecases;

import com.natvalentine.aggregateGame.Game;
import com.natvalentine.gateway.IBusEvent;
import com.natvalentine.gateway.IEventStore;
import com.natvalentine.generics.interfaces.IUseCaseExecute;
import com.natvalentine.player.Player;
import com.natvalentine.player.commands.CreatePlayerCommand;
import com.natvalentine.player.queries.responses.PlayerResponse;
import com.natvalentine.player.values.PlayerId;
import com.natvalentine.player.values.objects.Color;
import com.natvalentine.user.values.UserId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreatePlayerUseCase implements IUseCaseExecute<CreatePlayerCommand, PlayerResponse> {
    private final IEventStore eventRepository;
    private final IBusEvent busEvent;

    public CreatePlayerUseCase(IEventStore eventRepository, IBusEvent busEvent) {
        this.eventRepository = eventRepository;
        this.busEvent = busEvent;
    }

    @Override
    public Mono<PlayerResponse> execute(CreatePlayerCommand request) {
        var events = eventRepository.findAggregate(request.getAggregateId());
        return Game.from(request.getAggregateId(), events)
                .flatMap(game -> {
                    game.createPlayer(request.getUserId());
                    Player player = game.addPlayer(new Player(UserId.of(request.getUserId()), Color.of(""), new PlayerId()));

                    return game.getUncommittedEvents()
                            .flatMap(eventRepository::save)
                            .doOnNext(busEvent::sendEventPlayerCreated)
                            .then(Mono.just(new PlayerResponse(
                                    player.getId().getValue(),
                                    player.getUserId().getValue(),
                                    game.getId().getValue(),
                                    player.getColor().getValue()
                            )));
                });
    }
}
