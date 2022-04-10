package io.github.golden.runnable;

import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.golden.chat.ChatUtils;
import io.github.golden.queue.BaseQueue;
import io.github.golden.queue.QueueDeposit;

public class QueuePositionNotifier extends BukkitRunnable {

    private QueueDeposit queueDeposit;

    public QueuePositionNotifier(QueueDeposit queueDeposit) {
        this.queueDeposit = queueDeposit;
    }

    @Override
    public void run() {
        // get all the queues in the deposit
        Map<String, BaseQueue> queueMap = queueDeposit.getQueueMap();

        // for each queue
        for(BaseQueue queue : queueMap.values()) {

            // for each player in that queue
            for(Player player : queue.getPlayers()) {

                // send action bar message
                ChatUtils.sendActionBarMessage(player,
                    String.format("[%s] Your position in queue is: &a%s",
                        queue.getQueueName(),
                        queue.getPlayerPosition(player)
                ));
            }
        }
    }
    
}
