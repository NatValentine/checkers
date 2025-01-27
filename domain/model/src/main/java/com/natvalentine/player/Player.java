package com.natvalentine.player;

import com.natvalentine.generics.utils.Entity;
import com.natvalentine.player.values.PlayerId;
import com.natvalentine.player.values.objects.Color;
import com.natvalentine.player.values.objects.IsCurrent;
import com.natvalentine.user.values.UserId;

public class Player extends Entity<PlayerId> {
    private final UserId userId;
    private Color color;
    private IsCurrent isCurrent;

    public Player(PlayerId id, UserId userId) {
        super(id);
        this.userId = userId;
    }

    public Player(PlayerId id, UserId userId, Color color) {
        super(id);
        this.color = color;
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public IsCurrent getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(IsCurrent isCurrent) {
        this.isCurrent = isCurrent;
    }
}
