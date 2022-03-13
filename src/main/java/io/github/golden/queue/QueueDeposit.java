package io.github.golden.queue;

import java.util.HashMap;
import java.util.Queue;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.golden.event.PlayerAddedToQueueEvent;

public class QueueDeposit {
    // queueName - playerQueue
    private HashMap<String, Queue<Player>> queues;

    public QueueDeposit() {
        queues = new HashMap<>();
    }

    public BaseQueue store(BaseQueue baseQueue) {
        // load the queue in memory
        queues.put(baseQueue.getQueueName(), baseQueue.getCollection());
        return baseQueue;
    }

    public void addPlayerToQueue(String queueName, String playerName) {
        Player tmpPlayer = Bukkit.getPlayer(playerName);
        Queue<Player> tmpQueue = queues.get(queueName);

        // todo: add feedback
        if(tmpPlayer == null || tmpQueue == null) { return; }
        tmpQueue.add(tmpPlayer);

        // post event to all classes
        PlayerAddedToQueueEvent e = new PlayerAddedToQueueEvent(queueName, playerName);
        Bukkit.getPluginManager().callEvent(e);
    }

    public void removePlayerFromQueue(String queueName, String playerName) {
        Player tmpPlayer = Bukkit.getPlayer(playerName);
        Queue<Player> tmpQueue = queues.get(queueName);
        
        // todo: add feedback
        if(tmpPlayer == null || tmpQueue == null) { return; }
        tmpQueue.remove(tmpPlayer);

        // todo: might need to implement a player removed from queue event
        // but it's currently not needed
    }
}
