package io.github.golden.chat;

import org.bukkit.ChatColor;

public class TextComponent {
    private StringBuilder sb;

    private String string;
    private Object[] args;

    public TextComponent(String string, Object... args) {
        this.string = string;
        this.args = args;

        this.sb = new StringBuilder();
    }

    public String build(String prefix) {
        String translatedString = ChatColor.translateAlternateColorCodes('&', string);

        sb.append(prefix)
          .append(String.format(translatedString, args));

        return sb.toString();
    }
    
}
