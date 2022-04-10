package io.github.golden.command.commands;

import org.bukkit.entity.Player;

import io.github.golden.chat.ChatComponent;
import io.github.golden.chat.ChatUtils;
import io.github.golden.command.BaseCommand;
import io.github.golden.queue.QueueConfig;

public class RemoveQueueCommand extends BaseCommand {

    private QueueConfig queueConfig;

    public RemoveQueueCommand(QueueConfig queueConfig) {
        this.commandName        = "removequeue";
        this.requiredPermission = "sulfur.admin";
        this.requiredArgsLenght = 2;
        this.usage              = "/sulfur removequeue <name>";

        this.queueConfig        = queueConfig;
    }

    @Override
    protected void onCommand(Player executor, String... args) {
        queueConfig.removeQueue(args[1]);
        ChatUtils.sendMessage(executor, new ChatComponent("You've deleted the queue: \'%s\'", args[1]));
    }
}
