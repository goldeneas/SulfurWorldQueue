package io.github.golden.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.golden.chat.ChatUtils;
import io.github.golden.chat.ChatComponent;
import io.github.golden.queue.QueueConfig;
import io.github.golden.queue.QueueFactory;
import io.github.golden.queue.QueueResult;
import io.github.golden.queue.QueueType;

public class CommandListener implements CommandExecutor{

    private QueueConfig queueConfig = QueueConfig.getConfig();
    private QueueFactory queueFactory = new QueueFactory();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // todo: check if console is trying to join or leave queue
        
        if(!(sender instanceof Player)) { return false; }
        if(args.length < 1)             { return false; }

        Player player = (Player) sender;

        switch(args[0]) {
            // example: /sulfur createqueue normal testname hub factions 32
            case "createqueue":
                if(!player.hasPermission("sulfur.admin")) {
                    ChatUtils.sendMessage(player, 
                        new ChatComponent("Not enough permissions."));
                    return true; 
                }

                if(args.length < 6) {
                    ChatUtils.sendMessage(player,
                        new ChatComponent("Correct usage: %s", "/sulfur createqueue <normal/...> <name> <lobby> <destination> <maxplayers>"));
                    return true; 
                }

                QueueResult status = queueFactory.createQueue(QueueType.NORMAL, args[2], args[3], args[4], args[5]);
                if(status != QueueResult.OK) {
                    ChatUtils.sendMessage(player, new ChatComponent(""));
                }
                ChatUtils.sendMessage(player, new ChatComponent("You've created a new queue between %s and %s.", args[3], args[4]));
            break;

            // example: /sulfur removequeue name
            case "removequeue":
                if(!player.hasPermission("sulfur.admin")) {
                    ChatUtils.sendMessage(player, 
                        new ChatComponent("Not enough permissions."));
                    return true; 
                }

                if(args.length < 2) {
                    ChatUtils.sendMessage(player,
                        new ChatComponent("Correct usage: %s", "/sulfur removequeue <name>"));
                    return true;
                }
                queueConfig.removeQueue(args[1]);
                ChatUtils.sendMessage(player, new ChatComponent("You've deleted the queue: \'%s\'", args[1]));
            break;
            
            // example: /sulfur joinqueue testname
            case "joinqueue":

                if(!player.hasPermission("sulfur.user")) {
                    ChatUtils.sendMessage(player, 
                        new ChatComponent("Not enough permissions."));
                    return true; 
                }

                if(args.length < 2) {
                    ChatUtils.sendMessage(player,
                        new ChatComponent("Correct usage: %s", "/sulfur joinqueue <name>"));
                    return true;
                }
                
                // if the queue exists, add the player.
                // otherwise, do feedback
                if(queueFactory.getDeposit().addPlayerToQueue(args[1], sender.getName())) {
                    ChatUtils.sendMessage(player, new ChatComponent("You've joined the queue: \'%s\'", args[1]));
                } else {
                    ChatUtils.sendMessage(player, new ChatComponent("Could not find queue: \'%s\'", args[1]));
                }
            break;
            
            // example: /sulfur leavequeue testname
            case "leavequeue":

                if(!player.hasPermission("sulfur.user")) {
                    ChatUtils.sendMessage(player, 
                        new ChatComponent("Not enough permissions."));
                    return true; 
                }

                if(args.length < 2) {
                    ChatUtils.sendMessage(player,
                        new ChatComponent("Correct usage: %s", "/sulfur leavequeue <name>"));
                    return true;
                }

                // if the queue exists, remove the player.
                // otherwise, do feedback
                if(queueFactory.getDeposit().removePlayerFromQueue(args[1], sender.getName())) {
                    ChatUtils.sendMessage(player, new ChatComponent("You've left the queue: \'%s\'", args[1]));
                } else {
                    ChatUtils.sendMessage(player, new ChatComponent("Could not find queue: \'%s\'", args[1]));
                }
            break;

            // unknown command
            default:
                ChatUtils.sendMessage(player, new ChatComponent("Could not find command: \'%s\'", args[0]));
            break;
        }

        return true;
    }
}
