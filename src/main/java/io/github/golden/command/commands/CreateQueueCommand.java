package io.github.golden.command.commands;

import org.bukkit.entity.Player;

import io.github.golden.chat.ChatComponent;
import io.github.golden.chat.ChatUtils;
import io.github.golden.queue.QueueFactory;
import io.github.golden.queue.QueueResult;
import io.github.golden.queue.QueueType;

public class CreateQueueCommand extends BaseCommand {

    private QueueFactory queueFactory;

    public CreateQueueCommand(QueueFactory queueFactory) {
        this.commandName        = "createqueue";
        this.requiredPermission = "sulfur.admin";
        this.requiredArgsLenght = 6;
        this.usage              = "/sulfur createqueue <normal/...> <name> <lobby> <destination> <maxplayers>";

        this.queueFactory       = queueFactory;
    }

    @Override
    protected void onCommand(Player executor, String... args) {

        // parse the 5th arg as int (maxplayers)
        int maxPlayers = -1;
        try {
            maxPlayers = Integer.parseInt(args[5]);
        } catch(NumberFormatException e) {
            ChatUtils.sendMessage(executor,
                new ChatComponent("The value you inserted as 'maxplayers' is not a valid number.", maxPlayers));
            return;
        }

        // create the queue and save/load it
        QueueResult status = queueFactory.createQueue(QueueType.NORMAL, args[2], args[3], args[4], maxPlayers);

        // check the status of the queue
        // if the status is not ok, the queue will not be saved or loaded
        if(status != QueueResult.OK) {
            ChatUtils.sendMessage(executor, getErrorFeedback(status));
            return;
        }

        ChatUtils.sendMessage(executor, new ChatComponent("You've created a new queue between %s and %s.", args[3], args[4]));
    }

    // returns a correct feedback chatcomponent for the given status
    private ChatComponent getErrorFeedback(QueueResult status) {
        String error;

        switch (status) {
            case UNKNOWN_WORLD:
                error = "Unknown world.";
                break;

            case NOT_INITIALIZED:
                error = "The queue has not been initialized yet.";
                break;

            case NOT_IMPLEMENTED:
                error = "This feature has not been implemented yet.";
                break;

            default:
            case ERROR:
                error = "Unknown error.";
        }

        return new ChatComponent(error);
    }
    
}
