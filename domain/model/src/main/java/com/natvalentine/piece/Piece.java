package com.natvalentine.piece;

import com.natvalentine.generics.utils.ColorsEnum;
import com.natvalentine.generics.utils.Entity;
import com.natvalentine.piece.values.PieceId;
import com.natvalentine.piece.values.PieceTypesEnum;
import com.natvalentine.piece.values.objects.Color;
import com.natvalentine.piece.values.objects.IsActive;
import com.natvalentine.board.Tile;
import com.natvalentine.piece.values.objects.Type;
import com.natvalentine.player.Player;

import java.util.ArrayList;

public class Piece extends Entity<PieceId> {
    private Type type;
    private Color color;
    private IsActive isActive;
    private final Tile startingLocation;

    public Piece(PieceId id, Tile startingLocation, IsActive isActive, Color color, Type type) {
        super(id);
        this.startingLocation = startingLocation;
        this.isActive = isActive;
        this.color = color;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public IsActive getIsActive() {
        return isActive;
    }

    public void setIsActive(IsActive isActive) {
        this.isActive = isActive;
    }

    public Tile getStartingLocation() {
        return startingLocation;
    }

    @Override
    public String toString() {
        if (this.type.getValue().equals(PieceTypesEnum.PAWN.name()))
            return this.color.getValue().equals(ColorsEnum.WHITE.name()) ? "o" : "x";
        else return this.color.getValue().equals(ColorsEnum.WHITE.name()) ? "O" : "X";
    }
}
