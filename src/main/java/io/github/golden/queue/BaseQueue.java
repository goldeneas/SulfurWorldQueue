package io.github.golden.queue;

import java.util.LinkedList;
import java.util.Queue;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class BaseQueue {

    protected String queueName;
    protected World lobbyWorld;
    protected World destinationWorld;
    protected Queue<Player> playerQueue;
    protected int maxPlayers;

    protected BaseQueue() {
        playerQueue = new LinkedList<>();
    }

    public String getLobbyName() {
        return lobbyWorld.getName();
    }

    public String getDestinationName() {
        return destinationWorld.getName();
    }

    public String getQueueName() {
        return queueName;
    }

    public Queue<Player> getCollection() {
        return playerQueue;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}
