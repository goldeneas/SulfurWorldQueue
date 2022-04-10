package io.github.golden;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.golden.command.CommandListener;
import io.github.golden.command.completion.CommandCompleter;

public class Sulfur extends JavaPlugin {

    // important
    // todo: for the love of god fix the completion
    //  we might have to separate subcommand and options components
    //  we are still using the workaround, which is good for now
    //  because it's taking me way too much time for a stupid feature
    // todo: add config messages

    // less important
    // todo: remove player from other queues when joining a new one
    // todo: let the player write /leavequeue without specifying a queue name
    // todo: add permission based queues
    // todo: add priority queue

    private static Sulfur sulfur;

    @Override
    public void onEnable() {
        sulfur = this;

        this.getCommand("sulfur").setExecutor(new CommandListener());
        this.getCommand("sulfur").setTabCompleter(new CommandCompleter());
    }

    public static Sulfur getSulfur() {
        return sulfur;
    }

}
