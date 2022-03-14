package io.github.golden;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.golden.component.TextComponent;
import io.github.golden.queue.QueueConfig;
import io.github.golden.queue.QueueFactory;
import io.github.golden.queue.QueueType;

public class CommandListener implements CommandExecutor{

    private QueueConfig queueConfig = QueueConfig.getConfig();
    private QueueFactory queueFactory = new QueueFactory();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // todo: check for permissions
        
        if(!(sender instanceof Player)) { return false;}
        if(args.length < 1) { return false; }

        Player player = (Player) sender;

        switch(args[0]) {
            // example: /sulfur createqueue normal testname hub factions 32
            case "createqueue":
                if(args.length < 6) {
                    // todo: add real feedback messages
                    return false; 
                }
                queueFactory.createQueue(QueueType.NORMAL, args[2], args[3], args[4], args[5]);
                ChatUtils.sendMessage(player, new TextComponent("You've created a new queue between %s and %s.", args[3], args[4]));
            break;

            // example: /sulfur removequeue name
            case "removequeue":
                if(args.length < 2) {
                    // todo: add real feedback messages
                    return false;
                }
                queueConfig.removeQueue(args[1]);
                ChatUtils.sendMessage(player, new TextComponent("You've deleted the queue: \'%s\'", args[1]));
            break;
            
            // example: /sulfur joinqueue testname
            case "joinqueue":
                if(args.length < 2) {
                    // todo: add real feedback messages
                    return false;
                }

                // todo: add queue checking
                // (if it exists)

                queueFactory.getDeposit().addPlayerToQueue(args[1], sender.getName());
                ChatUtils.sendMessage(player, new TextComponent("You've joined the queue: \'%s\'", args[1]));
            break;
            
            // example: /sulfur leavequeue testname
            case "leavequeue":
                if(args.length < 2) {
                    // todo: add real feedback messages
                    return false;
                }

                queueFactory.getDeposit().removePlayerFromQueue(args[1], sender.getName());
                ChatUtils.sendMessage(player, new TextComponent("You've left the queue: \'%s\'", args[1]));
            break;

            // unknown command
            default:
            break;
        }

        return true;
    }
    
}
