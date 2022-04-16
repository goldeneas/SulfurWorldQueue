package io.github.golden.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.golden.event.PlayerAddedToQueueEvent;

public class QueueDeposit {

    private QueueConfig queueConfig = QueueConfig.getConfig();

    // queueName - playerQueue
    // TODO: IMPORTANT
    // probably an hashmap is not needed since we can iterate over the queue names with the getQueueName method
    // so make this a single dimension collection and then change the implementations
    private HashMap<String, BaseQueue> queues;

    public QueueDeposit() {
        queues = new HashMap<>();

        loadQueuesFromConfig();
    }

    public void loadQueuesFromConfig() {
        for(BaseQueue queue : queueConfig.getQueues()) {
            store(queue);
        }
    }

    public BaseQueue store(BaseQueue baseQueue) {
        // load the queue in memory
        queues.put(baseQueue.getQueueName(), baseQueue);
        return baseQueue;
    }

    public boolean addPlayerToQueue(String queueName, String playerName) {
        Player tmpPlayer = Bukkit.getPlayer(playerName);
        Queue<Player> tmpQueue = queues.get(queueName).getCollection();

        if(tmpPlayer == null || tmpQueue == null) { return false; }
        tmpQueue.add(tmpPlayer);

        // post event to all classes
        PlayerAddedToQueueEvent e = new PlayerAddedToQueueEvent(queueName, playerName);
        Bukkit.getPluginManager().callEvent(e);

        // don't return tmpQueue.add(player); otherwise we're gonna fire the event before we add the player
        /*
            Bukkit.callEvent(...)                   - event fired
            return tmpQueue.add(player)             - called afterwards
        */
        return true;
    }

    public boolean removePlayerFromQueue(String queueName, String playerName) {
        Player tmpPlayer = Bukkit.getPlayer(playerName);
        Queue<Player> tmpQueue = queues.get(queueName).getCollection();
        
        if(tmpPlayer == null || tmpQueue == null) { return false; }
        return tmpQueue.remove(tmpPlayer);
    }

    public BaseQueue getQueue(String queueName) {
        for(Map.Entry<String, BaseQueue> entry : queues.entrySet()) {
            if(queueName.equalsIgnoreCase(entry.getKey())) { return entry.getValue(); }
        }

        return null;
    }

    public Map<String, BaseQueue> getQueueMap() {
        return queues;
    }

    public boolean containsQueue(String queueName) {
        return (queues.get(queueName) != null);
    }
}
