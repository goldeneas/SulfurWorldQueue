package io.github.golden.command.commands;

import org.bukkit.entity.Player;

import io.github.golden.chat.ChatComponent;
import io.github.golden.chat.ChatUtils;
import io.github.golden.command.BaseCommand;
import io.github.golden.queue.QueueFactory;

public class LeaveQueueCommand extends BaseCommand {

    private QueueFactory queueFactory;
    
    public LeaveQueueCommand(QueueFactory queueFactory) {
        this.commandName        = "leavequeue";
        this.requiredArgsLenght = 2;
        this.usage              = "/sulfur leavequeue <name>";

        this.queueFactory       = queueFactory;
    }
    @Override
    protected void onCommand(Player executor, String... args) {
        // if the queue exists, remove the player.
        // otherwise, do feedback
        if(queueFactory.getDeposit().removePlayerFromQueue(args[1], executor.getName())) {
            ChatUtils.sendMessage(executor, new ChatComponent("You've left the queue: \'%s\'", args[1]));
        } else {
            ChatUtils.sendMessage(executor, new ChatComponent("Could not find queue: \'%s\'", args[1]));
        }
    }

}
