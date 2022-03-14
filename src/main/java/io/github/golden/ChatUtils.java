package io.github.golden;

import org.bukkit.entity.Player;

import io.github.golden.component.TextComponent;

public class ChatUtils {

    private static final String PREFIX = "§6Sulfur » §7";

    private ChatUtils() { throw new IllegalStateException("Utility class"); }

    public static void sendMessage(Player player, TextComponent textComponent) {
        player.sendMessage(textComponent.build(PREFIX));
    }

}
