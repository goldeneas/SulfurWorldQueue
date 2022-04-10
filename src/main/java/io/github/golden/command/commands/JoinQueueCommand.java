package io.github.golden.command.commands;

import org.bukkit.entity.Player;

import io.github.golden.chat.ChatComponent;
import io.github.golden.chat.ChatUtils;
import io.github.golden.queue.QueueDeposit;

public class JoinQueueCommand extends BaseCommand {

    private QueueDeposit queueDeposit;
    
    public JoinQueueCommand(QueueDeposit queueDeposit) {
        this.commandName        = "joinqueue";
        this.requiredArgsLenght = 2;
        this.usage              = "/sulfur joinqueue <name>";

        this.queueDeposit       = queueDeposit;
    }

    @Override
    protected void onCommand(Player executor, String... args) {
        String queueName = args[1];

        if(!queueDeposit.containsQueue(queueName)) {
            ChatUtils.sendMessage(executor,
                new ChatComponent("The queue '%s' was not found.", queueName));
            return;
        }

        // if the queue exists, add the player.
        // otherwise, do feedback
        if(queueDeposit.addPlayerToQueue(queueName, executor.getName())) {
            ChatUtils.sendMessage(executor, new ChatComponent("You've joined the queue: \'%s\'", args[1]));
        } else {
            ChatUtils.sendMessage(executor, new ChatComponent("Could not find queue: \'%s\'", args[1]));
        }
    }

}
