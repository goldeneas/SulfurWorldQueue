package io.github.golden.command.commands;

import org.bukkit.entity.Player;

import io.github.golden.chat.ChatComponent;
import io.github.golden.chat.ChatUtils;
import io.github.golden.queue.QueueDeposit;

public class LeaveQueueCommand extends BaseCommand {

    private QueueDeposit queueDeposit;
    
    public LeaveQueueCommand(QueueDeposit queueDeposit) {
        this.commandName        = "leavequeue";
        this.requiredArgsLenght = 2;
        this.usage              = "/sulfur leavequeue <name>";

        this.queueDeposit       = queueDeposit;
    }
    @Override
    protected void onCommand(Player executor, String... args) {
        String queueName = args[1];

        // todo: make this if (mainly the getQueue part) a bit shorter
        if(!queueDeposit.containsQueue(queueName) || !queueDeposit.getQueue(queueName).containsPlayer(executor)) {
            ChatUtils.sendMessage(executor,
                new ChatComponent("There was an issue leaving queue '%s'", queueName));
            return;
        }

        if(queueDeposit.removePlayerFromQueue(args[1], executor.getName())) {
            ChatUtils.sendMessage(executor, new ChatComponent("You've left the queue: '%s'", args[1]));
        } else {
            ChatUtils.sendMessage(executor, new ChatComponent("Could not find queue: '%s'", args[1]));
        }
    }

}
