package io.github.golden;

import org.bukkit.plugin.java.JavaPlugin;

public class Sulfur extends JavaPlugin {

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
