package com.natvalentine.board.commands.usecases;

import com.natvalentine.aggregateGame.Game;
import com.natvalentine.board.Board;
import com.natvalentine.board.Tile;
import com.natvalentine.board.commands.CreateBoardCommand;
import com.natvalentine.board.queries.responses.BoardResponse;
import com.natvalentine.gateway.IBusEvent;
import com.natvalentine.gateway.IEventStore;
import com.natvalentine.generics.interfaces.IUseCaseExecute;
import com.natvalentine.generics.utils.ColorsEnum;
import com.natvalentine.piece.Piece;
import com.natvalentine.piece.values.PieceId;
import com.natvalentine.piece.values.PieceTypesEnum;
import com.natvalentine.piece.values.objects.Color;
import com.natvalentine.piece.values.objects.IsActive;
import com.natvalentine.piece.values.objects.Type;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class CreateBoardUseCase implements IUseCaseExecute<CreateBoardCommand, BoardResponse> {
    private final IEventStore eventRepository;
    private final IBusEvent busEvent;

    public CreateBoardUseCase(IEventStore eventRepository, IBusEvent busEvent) {
        this.eventRepository = eventRepository;
        this.busEvent = busEvent;
    }

    @Override
    public Mono<BoardResponse> execute(CreateBoardCommand request) {
        var events = eventRepository.findAggregate(request.getAggregateId());
        return Game.from(request.getAggregateId(), events)
                .flatMap(game -> {
                        game.createBoard(generateTiles());

                        Board newBoard = game.getBoard();
                        if (newBoard == null) {
                            throw new RuntimeException("Board was not created.");
                        }

                        setPieces(newBoard);

                        return game.getUncommittedEvents()
                            .flatMap(eventRepository::save)
                            .doOnNext(busEvent::sendEventBoardCreated)
                            .then(Mono.just(new BoardResponse(
                                    newBoard.getId().getValue(),
                                    newBoard.getTiles(),
                                    game.getId().getValue()
                            )));
                });
    }

    private ArrayList<Tile> generateTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int y = 1; y < 9; y++) {
            for (int x = 1; x < 9; x++) {
                tiles.add(new Tile(x, y));
            }
        }
        return tiles;
    }

    private void setPieces(Board board) {
        // White pieces:
        // 1,1 1,3 1,5 1,7
        // 3,1 3,3 3,5 3,7
        board.getTiles().stream().filter(
                        t -> t.getLocation().getValue().x() == 1 || t.getLocation().getValue().x() == 3)
                .forEach(t -> {
                    if (t.getLocation().getValue().y() % 2 == 0)
                        t.setPiece(new Piece(
                                new PieceId(),
                                t,
                                IsActive.of(true),
                                Color.of(ColorsEnum.WHITE.name()),
                                Type.of(PieceTypesEnum.PAWN.name())));
                });
        // 2,2 2,4 2,6 2,8
        board.getTiles().stream().filter(t -> t.getLocation().getValue().x() == 2).forEach(t -> {
            if (t.getLocation().getValue().y() % 2 != 0)
                t.setPiece(new Piece(
                        new PieceId(),
                        t,
                        IsActive.of(true),
                        Color.of(ColorsEnum.WHITE.name()),
                        Type.of(PieceTypesEnum.PAWN.name())));
        });

        // Black pieces:
        // 8,2 8,4 8,6 8,8
        // 6,2 6,4 6,6 6,8
        board.getTiles().stream().filter(
                        t -> t.getLocation().getValue().x() == 8 || t.getLocation().getValue().x() == 6)
                .forEach(t -> {
                    if (t.getLocation().getValue().y() % 2 == 0)
                        t.setPiece(new Piece(
                                new PieceId(),
                                t,
                                IsActive.of(true),
                                Color.of(ColorsEnum.BLACK.name()),
                                Type.of(PieceTypesEnum.PAWN.name())));
                });
        // 7,1 7,3 7,5 7,7
        board.getTiles().stream().filter(t -> t.getLocation().getValue().x() == 7)
                .forEach(t -> {
                    if (t.getLocation().getValue().y() % 2 != 0)
                        t.setPiece(new Piece(
                                new PieceId(),
                                t,
                                IsActive.of(true),
                                Color.of(ColorsEnum.BLACK.name()),
                                Type.of(PieceTypesEnum.PAWN.name())));
                });
    }
}
