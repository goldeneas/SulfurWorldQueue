package io.github.golden.chat;

import org.bukkit.entity.Player;

public class ChatUtils {

    private static final String PREFIX = "§6Sulfur » §7";

    private ChatUtils() { throw new IllegalStateException("Utility class"); }

    public static void sendMessage(Player player, TextComponent textComponent) {
        player.sendMessage(textComponent.build(PREFIX));
    }

}