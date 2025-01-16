package com.natvalentine.aggregateGame.events;

import com.natvalentine.board.Board;
import com.natvalentine.generics.domain.DomainEvent;
import com.natvalentine.generics.utils.ColorsEnum;
import com.natvalentine.piece.Piece;
import com.natvalentine.piece.values.PieceId;
import com.natvalentine.piece.values.PieceTypesEnum;
import com.natvalentine.piece.values.objects.IsActive;
import com.natvalentine.player.Player;

import java.util.ArrayList;

public class PiecesSet extends DomainEvent {
    private Board board;
    private ArrayList<Player> players;

    public PiecesSet(Board board, ArrayList<Player> players) {
        super(GameEventsEnum.PIECES_SET.name());
        this.board = board;
        this.players = players;
        setPieces();
    }

    private void setPieces() {
        // White pieces:
        // 1,1 1,3 1,5 1,7
        // 3,1 3,3 3,5 3,7
        this.board.getTiles().stream().filter(
                t -> t.getLocation().getValue().x() == 1 || t.getLocation().getValue().x() == 3)
                .forEach(t -> {
                    if (t.getLocation().getValue().y() % 2 == 0)
                        t.setPiece(new Piece(
                                new PieceId(),
                               PieceTypesEnum.PAWN,
                                t,
                                IsActive.of(true),
                                this.players.stream().filter(
                                        p -> p.getColor().getValue().equals(ColorsEnum.WHITE.name()))
                                        .findFirst().get()));
        });
        // 2,2 2,4 2,6 2,8
        this.board.getTiles().stream().filter(t -> t.getLocation().getValue().x() == 2).forEach(t -> {
            if (t.getLocation().getValue().y() % 2 != 0)
                t.setPiece(new Piece(
                    new PieceId(),
                    PieceTypesEnum.PAWN,
                    t,
                    IsActive.of(true),
                    this.players.stream().filter(
                            p -> p.getColor().getValue().equals(ColorsEnum.WHITE.name()))
                            .findFirst().get()));
        });

        // Black pieces:
        // 8,2 8,4 8,6 8,8
        // 6,2 6,4 6,6 6,8
        this.board.getTiles().stream().filter(
                t -> t.getLocation().getValue().x() == 8 || t.getLocation().getValue().x() == 6)
                .forEach(t -> {
                    if (t.getLocation().getValue().y() % 2 == 0)
                        t.setPiece(new Piece(
                                new PieceId(),
                                PieceTypesEnum.PAWN,
                                t,
                                IsActive.of(true),
                                this.players.stream().filter(
                                        p -> p.getColor().getValue().equals(ColorsEnum.BLACK.name()))
                                        .findFirst().get()));
        });
        // 7,1 7,3 7,5 7,7
        this.board.getTiles().stream().filter(t -> t.getLocation().getValue().x() == 7)
                .forEach(t -> {
                    if (t.getLocation().getValue().y() % 2 != 0)
                        t.setPiece(new Piece(
                            new PieceId(),
                            PieceTypesEnum.PAWN,
                            t,
                            IsActive.of(true),
                            this.players.stream().filter(
                                    p -> p.getColor().getValue().equals(ColorsEnum.BLACK.name()))
                                    .findFirst().get()));
        });
    }
}
