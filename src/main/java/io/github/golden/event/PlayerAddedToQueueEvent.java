package io.github.golden.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerAddedToQueueEvent extends Event{
    private static final HandlerList HANDLERS = new HandlerList();

    private String queueName;
    private Player player;

    public PlayerAddedToQueueEvent(String queueName, String playerName) {
        this.queueName = queueName;
        this.player = Bukkit.getPlayer(playerName);
    }

    public String getQueueName() {
        return queueName;
    }

    public Player getPlayer() {
        return player;
    }

    // don't remove these, they're both needed
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
