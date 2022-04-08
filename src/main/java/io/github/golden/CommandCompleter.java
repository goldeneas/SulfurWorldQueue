package io.github.golden;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import io.github.golden.completition.CompletionComponent;
import io.github.golden.completition.CompletionFactory;

// todo: probably refactor this even more
public class CommandCompleter implements TabCompleter {

    private CompletionFactory completionFactory = new CompletionFactory();

    public CommandCompleter() {
        // create auto completion components
        completionFactory.createComponent("", 
            "createqueue/removequeue/joinqueue/leavequeue");

        completionFactory.createComponent("createqueue",
            "normal/priority", "<queue_name>", "<lobby>", "<destination>", "<max_players>");
        
        completionFactory.createComponent("removequeue",
            "<queue_name>");

        completionFactory.createComponent("joinqueue",
            "<queue_name>");

        completionFactory.createComponent("leavequeue",
            "<queue_name>");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // get the components
        for(CompletionComponent c : completionFactory.getComponents()) {
            // check if the current component's command name is the same as sulfur's first subcommand
            // we have to trim it in order to remove trailing and starting spaces
            // for example:
            /*
                        0           1      2    3     4        5
                /sulfur createqueue normal test lobby factions 32
            */
            if(c.getCommandName().equalsIgnoreCase(args[0].trim())) {
                // the lenght of the args starts from 1 (we always have an argument, starting from the subcommand)
                // we remove one because as we type the subcommand, the lenght of args is already one
                // but we want to store it as the 0th arg
                // ex.
                /*
                    ARGS:                   0           1    ...
                    LENGHT:                 1           2
                                    /sulfur createqueue test ...
                */
                return c.getOptions(args.length - 1);
            }
        }

        return Collections.emptyList();
    }
    
}
