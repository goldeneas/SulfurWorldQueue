package io.github.golden;

import org.bukkit.ChatColor;

public class TextComponent {

    private static final String PREFIX = "§6[SULFUR] > §7";

    private StringBuilder sb;

    private String string;
    private Object[] args;

    public TextComponent(String string, Object... args) {
        this.string = string;
        this.args = args;

        this.sb = new StringBuilder();
    }

    public String build() {
        String translatedString = ChatColor.translateAlternateColorCodes('&', string);

        sb.append(PREFIX)
          .append(String.format(translatedString, args));

        return sb.toString();
    }
    
}
