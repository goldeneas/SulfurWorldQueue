package io.github.golden.command.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.bukkit.entity.Player;

import io.github.golden.chat.ChatComponent;
import io.github.golden.chat.ChatUtils;
import io.github.golden.queue.BaseQueue;
import io.github.golden.queue.QueueDeposit;

public class ListQueuesCommand extends BaseCommand {

    private QueueDeposit queueDeposit;

    public ListQueuesCommand(QueueDeposit queueDeposit) {
        this.commandName        = "listqueues";
        this.requiredPermission = "sulfur.admin";
        this.requiredArgsLenght = 1;
        this.usage              = "/sulfur listqueues";

        this.queueDeposit = queueDeposit;
    }

    // todo: check if this command works

    @Override
    protected void onCommand(Player executor, String... args) {
        Collection<BaseQueue> queues = queueDeposit.getQueueMap().values();
        if(queues.isEmpty()) {
            ChatUtils.sendMessage(executor, new ChatComponent("There are no registered queues."));
            return;
        }

        ChatUtils.sendMessage(executor, new ChatComponent("Here's a list of the currently active queues."));

        // for each queue
        queues.forEach((BaseQueue queue) -> {
            ChatUtils.sendMessage(executor,
                new ChatComponent("%s - [%s/%s]",
                    queue.getQueueName(),
                    queue.getPlayers().size(),
                    queue.getMaxPlayers()
                ));
        });
    }
}
