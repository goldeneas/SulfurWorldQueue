package io.github.golden;

import org.bukkit.plugin.java.JavaPlugin;

public class Sulfur extends JavaPlugin {

    // important
    // todo: add on event queue loading
    // todo: add permissions
    // todo: add sidebar/lowbar info
    // todo: add config messages

    // less important
    // todo: add priority queue

    private static Sulfur sulfur;

    @Override
    public void onEnable() {
        sulfur = this;

        this.getCommand("sulfur").setExecutor(new CommandListener());
    }

    public static Sulfur getSulfur() {
        return sulfur;   
    }

}
