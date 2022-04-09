package io.github.golden;

import org.bukkit.plugin.java.JavaPlugin;

public class Sulfur extends JavaPlugin {

    // important
    // todo: fix autocomplete workaround
    // todo: add config messages

    // less important
    // todo: add permission based queues
    // todo: add sidebar/lowerbar info
    // todo: add command class for commands
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
