package io.github.golden.command;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.golden.Sulfur;
import io.github.golden.command.commands.BaseCommand;
import io.github.golden.command.commands.CreateQueueCommand;
import io.github.golden.command.commands.JoinQueueCommand;
import io.github.golden.command.commands.LeaveQueueCommand;
import io.github.golden.command.commands.RemoveQueueCommand;
import io.github.golden.queue.QueueConfig;
import io.github.golden.queue.QueueDeposit;
import io.github.golden.queue.QueueFactory;
import io.github.golden.runnable.QueuePositionNotifier;

public class CommandListener implements CommandExecutor{

    private static Sulfur sulfur = Sulfur.getSulfur();
    private QueueConfig queueConfig = QueueConfig.getConfig();

    private QueueDeposit queueDeposit = new QueueDeposit();
    private QueueFactory queueFactory = new QueueFactory(queueDeposit);

    private ArrayList<BaseCommand> commands = new ArrayList<>();

    public CommandListener() {
        // create the commands
        commands.add(new CreateQueueCommand(queueFactory));
        commands.add(new RemoveQueueCommand(queueDeposit, queueConfig));
        commands.add(new LeaveQueueCommand(queueDeposit));
        commands.add(new JoinQueueCommand(queueDeposit));

        // notify every player for each queue in this instance's deposit
        // about their position in the queue.
        new QueuePositionNotifier(queueDeposit).runTaskTimer(sulfur, 100, 40);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // todo: check if console is trying to join or leave queue
        if(!(sender instanceof Player)) { return false; }
        if(args.length < 1)             { return false; }

        Player player = (Player) sender;
        String commandName = args[0];

        for(BaseCommand c : commands) {
            if(commandName.equalsIgnoreCase(c.getCommandName())) { c.invoke(player, args); return true; }
        }

        return true;
    }
}
