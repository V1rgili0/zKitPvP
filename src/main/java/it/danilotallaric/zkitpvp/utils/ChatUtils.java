package it.danilotallaric.zkitpvp.utils;

import it.danilotallaric.zkitpvp.KitPvP;
import org.bukkit.ChatColor;

public class ChatUtils {
    public static String getColoredText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String getFormattedText(String path) {
        return getColoredText(KitPvP.getFileManager().getMessages().getString(path));
    }
}
