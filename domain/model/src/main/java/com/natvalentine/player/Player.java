package com.natvalentine.player;

import com.natvalentine.generics.utils.Entity;
import com.natvalentine.player.values.PlayerId;
import com.natvalentine.player.values.objects.Color;
import com.natvalentine.player.values.objects.IsCurrent;
import com.natvalentine.user.values.UserId;

public class Player extends Entity<PlayerId> {
    private final UserId userId;
    private final Color color;
    private IsCurrent isCurrent;

    public Player(UserId userId, Color color, PlayerId id) {
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

    public IsCurrent getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(IsCurrent isCurrent) {
        this.isCurrent = isCurrent;
    }
}
