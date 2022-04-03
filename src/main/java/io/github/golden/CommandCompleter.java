package io.github.golden;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import io.github.golden.completition.CompletionComponent;

// todo: probably refactor this even more
public class CommandCompleter implements TabCompleter {

    private List<CompletionComponent> completionMap;

    public CommandCompleter() {
        CompletionComponent createQueue = new CompletionComponent()
            .setCommandName("createqueue")
            .build("normal/priority", "<queue_name>", "<lobby>", "destination", "<max_players>");
        completionMap.add(createQueue);

        CompletionComponent removeQueue = new CompletionComponent()
            .setCommandName("removequeue")
            .build("<queue_name>");
        completionMap.add(removeQueue);

        CompletionComponent joinQueue = new CompletionComponent()
            .setCommandName("joinqueue")
            .build("<queue_name>");
        completionMap.add(joinQueue);

        CompletionComponent leaveQueue = new CompletionComponent()
            .setCommandName("leavequeue")
            .build("<queue_name>");
        completionMap.add(leaveQueue);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        for() {
            
        }

    }
    
}
