package io.github.golden.command;

import org.bukkit.entity.Player;

import io.github.golden.chat.ChatComponent;
import io.github.golden.chat.ChatUtils;

public abstract class BaseCommand {
    
    protected String commandName;
    protected String requiredPermission;
    protected int requiredArgsLenght;

    protected String usage;

    protected abstract void onCommand(Player executor, String... args);

    // this is the method that has to be called
    // in order to execute a subclass's command
    public void invoke(Player executor, String... args) {
        if(args.length < requiredArgsLenght) { 
            ChatUtils.sendMessage(executor,
                new ChatComponent("Correct usage: %s", usage));
            return;
        }

        if(!executor.hasPermission(requiredPermission) || (!requiredPermission.equals(""))) {
            ChatUtils.sendMessage(executor, 
                new ChatComponent("Not enough permissions."));
            return;    
        }

        // if the conditions are met, we can execute the command
        onCommand(executor, args);
    }

    public String getCommandName() {
        return commandName;
    }

    public String getPermission() {
        return requiredPermission;
    }

}
