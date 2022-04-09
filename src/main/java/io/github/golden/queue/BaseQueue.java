package io.github.golden.queue;

import java.util.ArrayDeque;
import java.util.Queue;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class BaseQueue {

    protected String queueName;
    protected World lobbyWorld;
    protected World destinationWorld;
    protected ArrayDeque<Player> playerQueue;
    protected int maxPlayers;

    protected BaseQueue() {
        playerQueue = new ArrayDeque<>();
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

    public int getPlayerPosition(Player player) {
        // will return -1 if the player is not in the queue
        if(!playerQueue.contains(player)) { return -1; }

        // start the index at 1
        // the first player has to be in position 1, not 0
        int index = 1;
        String playerName = player.getName();

        // todo: check order of iterator
        // if it's FIFO (most likely) or LIFO
        for(Player p : playerQueue) {
            if(playerName.equalsIgnoreCase(p.getName())) { break; }
            index++;
        }

        return index;
    }
}
