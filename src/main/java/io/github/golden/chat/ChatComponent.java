package io.github.golden.chat;

import org.bukkit.ChatColor;

public class ChatComponent {
    private StringBuilder sb;

    private String message;
    private Object[] formatArgs;

    public ChatComponent(String message, Object... formatArgs) {
        this.message = message;
        this.formatArgs = formatArgs;

        this.sb = new StringBuilder();
    }

    public String build(String prefix) {
        String translatedPrefix = ChatColor.translateAlternateColorCodes('&', prefix);
        String translatedString = ChatColor.translateAlternateColorCodes('&', message);

        sb.append(translatedPrefix)
          .append(String.format(translatedString, formatArgs));

        return sb.toString();
    }
    
}
