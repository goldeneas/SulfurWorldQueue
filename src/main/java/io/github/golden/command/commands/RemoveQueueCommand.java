package io.github.golden.command.commands;

import org.bukkit.entity.Player;

import io.github.golden.chat.ChatComponent;
import io.github.golden.chat.ChatUtils;
import io.github.golden.queue.QueueConfig;
import io.github.golden.queue.QueueDeposit;

public class RemoveQueueCommand extends BaseCommand {

    private QueueConfig queueConfig;
    private QueueDeposit queueDeposit;

    public RemoveQueueCommand(QueueDeposit queueDeposit, QueueConfig queueConfig) {
        this.commandName        = "removequeue";
        this.requiredPermission = "sulfur.admin";
        this.requiredArgsLenght = 2;
        this.usage              = "/sulfur removequeue <name>";

        this.queueConfig        = queueConfig;
        this.queueDeposit       = queueDeposit;
    }

    @Override
    protected void onCommand(Player executor, String... args) {
        String queueName = args[1];

        if(!queueDeposit.containsQueue(queueName)) {
            ChatUtils.sendMessage(executor,
                new ChatComponent("The queue '%s' was not found", queueName));
            return;
        }

        queueConfig.removeQueue(queueName);
        ChatUtils.sendMessage(executor, new ChatComponent("You've deleted the queue: '%s'", queueName));
    }
}
