package it.danilotallaric.zkitpvp.placeholders;

import it.danilotallaric.zkitpvp.KitPvP;
import it.danilotallaric.zkitpvp.data.PlayerData;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class MainPlaceholder extends PlaceholderExpansion {
    public @NotNull String getAuthor() {
        return "Virgili0_";
    }

    public @NotNull String getIdentifier() {
        return "zKitPvP";
    }

    public @NotNull String getVersion() {
        return "1.0.2";
    }

    public String onRequest(OfflinePlayer player, String params) {
        long endTimestamp, diff, current = System.currentTimeMillis();
        PlayerData data = KitPvP.getDataManager().getPlayerData(player.getPlayer().getUniqueId());
        switch (params) {
            case "bounty":
                return String.valueOf(data.getBounty());
            case "bounty_formatted":
                return formatNumber(data.getBounty());
            case "kd":
                return String.format("%.2f", data.getKD());
            case "kills":
                return String.valueOf(data.kills);
            case "deaths":
                return String.valueOf(data.deaths);
            case "streak":
                return String.valueOf(data.streak);
            case "combat":
                if (data.endCombatTimestamp == -1L)
                    return "0.0";
                endTimestamp = data.endCombatTimestamp;
                diff = endTimestamp - current;
                if (diff > 0L) {
                    double secondsRemaining = diff / 1000.0D;
                    return String.format("%.1f", Math.floor(secondsRemaining * 10.0D) / 10.0D);
                }
                data.endCombatTimestamp = -1L;
                return "0.0";
            case "enderpearl":
                if (data.endEnderTimestamp == -1L)
                    return "0.0";
                endTimestamp = data.endEnderTimestamp;
                diff = endTimestamp - current;
                if (diff > 0L) {
                    double secondsRemaining = diff / 1000.0D;
                    return String.format("%.1f", Math.floor(secondsRemaining * 10.0D) / 10.0D);
                }
                data.endEnderTimestamp = -1L;
                return "0.0";
        }
        return null;
    }

    private String formatNumber(long value) {
        String number = String.format("%,d", value);
        String[] commas = {"K", "M", "B", "T", "Q"};
        String[] split = number.split(",");
        if (split.length == 1)
            return String.valueOf(value);
        return split[0] + "." + split[1].charAt(0) + commas[split.length - 2];
    }
}
