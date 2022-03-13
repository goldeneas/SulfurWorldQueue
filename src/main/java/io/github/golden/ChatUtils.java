package io.github.golden;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

// todo: remake this
public class ChatUtils {
    private ChatUtils() { throw new IllegalStateException("Utility class"); }
    
    private static final String PREFIX = "§6[SULFUR] > §7";

    public static void sendMessage(Player player, String message) {
        String translatedMsg = ChatColor.translateAlternateColorCodes('&', message);
        player.sendMessage(PREFIX + translatedMsg);
    }

}
