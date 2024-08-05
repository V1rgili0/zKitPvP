package it.danilotallaric.zkitpvp.events;

import it.danilotallaric.zkitpvp.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class KillStreakEvent extends Event {
    private final Player player;

    private final int streak;

    public Player getPlayer() {
        return this.player;
    }

    public int getStreak() {
        return this.streak;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    private static final HandlerList handlerList = new HandlerList();

    public KillStreakEvent(Player player, int streak) {
        this.player = player;
        this.streak = streak;
        Bukkit.broadcastMessage(ChatUtils.getFormattedText("stats.broadcast-streak").replaceAll("%player%", player.getName()).replaceAll("%streak%", String.valueOf(streak)));
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlerList;
    }
}
