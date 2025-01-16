package com.natvalentine.player.commands.usecases;

import com.natvalentine.aggregateGame.Game;
import com.natvalentine.aggregateGame.values.GameId;
import com.natvalentine.gateway.IBusEvent;
import com.natvalentine.gateway.IEventStore;
import com.natvalentine.generics.interfaces.IUseCaseExecute;
import com.natvalentine.player.Player;
import com.natvalentine.player.commands.CreatePlayerCommand;
import com.natvalentine.player.queries.responses.PlayerResponse;
import com.natvalentine.player.values.PlayerId;
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
        var gameId = request.getAggregateId() != null ? request.getAggregateId() : new GameId().getValue();
        return Game.from(gameId, events)
                .flatMap(game -> {
                    var playerId = new PlayerId().getValue();
                    game.createPlayer(playerId, request.getUserId());

                    Player newPlayer;
                    if (game.getWhitePlayer().getId().getValue().equals(playerId))
                        newPlayer = game.getWhitePlayer();
                    else if (game.getBlackPlayer() != null && game.getBlackPlayer().getId().getValue().equals(playerId))
                        newPlayer = game.getBlackPlayer();
                    else throw new RuntimeException("Player could not be created.");

                    return game.getUncommittedEvents()
                            .flatMap(eventRepository::save)
                            .doOnNext(busEvent::sendEventPlayerCreated)
                            .then(Mono.just(new PlayerResponse(
                                    newPlayer.getId().getValue(),
                                    newPlayer.getUserId().getValue(),
                                    game.getId().getValue(),
                                    newPlayer.getColor().getValue(),
                                    newPlayer.getIsCurrent().getValue()
                            )));
                });
    }
}
