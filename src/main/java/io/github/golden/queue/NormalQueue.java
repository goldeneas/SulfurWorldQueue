package io.github.golden.queue;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.golden.Sulfur;
import io.github.golden.event.PlayerAddedToQueueEvent;

public class NormalQueue extends BaseQueue implements Listener {

    private static Sulfur sulfur = Sulfur.getSulfur();

    public NormalQueue(String queueName, String lobbyName, String destinationName, int maxPlayers) {
        // set super class variables
        this.queueName          = queueName;
        this.maxPlayers         = maxPlayers;
        this.lobbyWorld         = sulfur.getServer().getWorld(lobbyName);
        this.destinationWorld   = sulfur.getServer().getWorld(destinationName);
        this.status             = checkStatus();

        // if the queue is ok, then we can register the events
        if(this.status == QueueResult.OK) { sulfur.getServer().getPluginManager().registerEvents(this, sulfur); }
    }

    // todo: commented out because we don't want anyone to automatically join the queue
    // might be implemented as a feature though
    // @EventHandler
    // public void onPlayerJoin(PlayerJoinEvent event) {
    //     if(event.getPlayer().getWorld() != lobbyWorld) { return; }

    //     // the player has to enter the queue
    //     if(destinationWorld.getPlayers().size() >= maxPlayers) {
    //         queue.add(event.getPlayer());
    //     }
    // }

    @EventHandler
    public void onPlayerAddedToQueue(PlayerAddedToQueueEvent event) {
        // if the queue the player has been added to is not this class's, then don't do anything here
        if(!(queueName.equalsIgnoreCase(event.getQueueName()))) { return; }

        // if the world is not currently full, then teleport the player
        if(destinationWorld.getPlayers().size() < maxPlayers) {
            Player player = event.getPlayer();
            player.teleport(destinationWorld.getSpawnLocation());
        }
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        // if the player has changed world, remove him from the queue
        if(event.getFrom() == lobbyWorld && player.getWorld() != lobbyWorld) {
            // todo: might cause a double removal of player
            // if this doesn't throw an error, it's fine
            playerQueue.remove(player);
        }

        // if the player has left the destination world, poll another player from the queue
        if(event.getFrom() == destinationWorld && player.getWorld() != destinationWorld) {
            Player firstPlayer = playerQueue.poll();
            firstPlayer.teleport(destinationWorld.getSpawnLocation());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if(event.getPlayer().getWorld() != destinationWorld) { return; }

        // get next player in queue
        // if the queue is empty, return
        Player firstPlayer = playerQueue.poll();
        if(firstPlayer == null) { return; }

        firstPlayer.teleport(destinationWorld.getSpawnLocation());
    }
    
}
