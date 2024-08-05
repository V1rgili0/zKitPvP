package it.danilotallaric.zkitpvp.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class BountyClaimEvent extends Event {
    private final Player killed;

    private final Player killer;

    private final Location deathLocation;

    private final long bounty;

    public Player getKilled() {
        return this.killed;
    }

    public Player getKiller() {
        return this.killer;
    }

    public Location getDeathLocation() {
        return this.deathLocation;
    }

    public long getBounty() {
        return this.bounty;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    private static final HandlerList handlerList = new HandlerList();

    public BountyClaimEvent(Player killed, Player killer, Location deathLocation, long bounty) {
        this.deathLocation = deathLocation;
        this.killed = killed;
        this.killer = killer;
        this.bounty = bounty;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlerList;
    }
}
