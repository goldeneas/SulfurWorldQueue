package io.github.golden.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatUtils {

    private static final String PREFIX = "&6Sulfur » &7";

    private ChatUtils() { throw new IllegalStateException("Utility class"); }

    public static void sendMessage(Player player, ChatComponent chatComponent) {
        player.sendMessage(chatComponent.build(PREFIX));
    }

    public static void sendActionBarMessage(Player player, String message) {
        String translatedMessage = ChatColor.translateAlternateColorCodes('&', message);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(translatedMessage));
    }
}