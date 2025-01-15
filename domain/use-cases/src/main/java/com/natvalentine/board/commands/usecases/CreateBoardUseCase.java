package com.natvalentine.board.commands.usecases;

import com.natvalentine.board.commands.CreateBoardCommand;
import com.natvalentine.board.queries.responses.BoardResponse;
import com.natvalentine.gateway.IBusEvent;
import com.natvalentine.gateway.IEventStore;
import com.natvalentine.generics.interfaces.IUseCaseExecute;
import reactor.core.publisher.Mono;

public class CreateBoardUseCase implements IUseCaseExecute<CreateBoardCommand, BoardResponse> {
    private final IEventStore eventRepository;
    private final IBusEvent busEvent;

    public CreateBoardUseCase(IEventStore eventRepository, IBusEvent busEvent) {
        this.eventRepository = eventRepository;
        this.busEvent = busEvent;
    }


    @Override
    public Mono<BoardResponse> execute(CreateBoardCommand request) {
        return null;
    }
}
