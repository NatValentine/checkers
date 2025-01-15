package com.natvalentine.piece;

import com.natvalentine.generics.utils.ColorsEnum;
import com.natvalentine.generics.utils.Entity;
import com.natvalentine.piece.values.PieceId;
import com.natvalentine.piece.values.PieceTypesEnum;
import com.natvalentine.piece.values.objects.IsActive;
import com.natvalentine.board.Tile;
import com.natvalentine.player.Player;

import java.util.ArrayList;

public class Piece extends Entity<PieceId> {
    private PieceTypesEnum type;
    private final Tile startingLocation;
    private Tile currentLocation;
    private IsActive isActive;
    private final Player player;

    public Piece(PieceId id, PieceTypesEnum type, Tile startingLocation, IsActive isActive, Player player) {
        super(id);
        this.type = type;
        this.startingLocation = startingLocation;
        this.currentLocation = startingLocation;
        this.isActive = isActive;
        this.player = player;
    }

    private ArrayList<Tile> calculatePosibleMoves() {
        return new ArrayList<>();
    }

    public PieceTypesEnum getType() {
        return type;
    }

    public void setType(PieceTypesEnum type) {
        this.type = type;
    }

    public Tile getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Tile currentLocation) {
        this.currentLocation = currentLocation;
    }

    public IsActive getIsActive() {
        return isActive;
    }

    public void setIsActive(IsActive isActive) {
        this.isActive = isActive;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        if (this.type.equals(PieceTypesEnum.PAWN))
            return this.player.getColor().getValue().equals(ColorsEnum.WHITE.name()) ? "o" : "x";
        else return this.player.getColor().getValue().equals(ColorsEnum.WHITE.name()) ? "O" : "X";
    }
}
