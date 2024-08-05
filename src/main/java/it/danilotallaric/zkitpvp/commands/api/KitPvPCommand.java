package it.danilotallaric.zkitpvp.commands.api;

import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class KitPvPCommand implements CommandExecutor {
    private String noPermissionMsg;

    private String onlyPlayerMsg;

    private String noSubCommandFoundMsg;

    private final String name;

    private final String permission;

    private final boolean playerOnly;

    private final JavaPlugin plugin;

    public KitPvPCommand(JavaPlugin plugin, String name, String permission, boolean playerOnly) {
        this.plugin = plugin;
        this.name = name;
        this.permission = permission;
        this.playerOnly = playerOnly;
    }

    public String getName() {
        return this.name;
    }

    public String getPermission() {
        return this.permission;
    }

    public boolean isPlayerOnly() {
        return this.playerOnly;
    }

    public void registerExecutor() {
        this.plugin.getCommand(this.name).setExecutor(this);
    }

    public void setNoPermissionMessage(String message) {
        this.noPermissionMsg = message;
    }

    public void setNoSubCommandFoundMessage(String message) {
        this.noSubCommandFoundMsg = message;
    }

    public void setOnlyPlayerMessage(String message) {
        this.onlyPlayerMsg = message;
    }

    public abstract boolean execute(CommandSender paramCommandSender, List<String> paramList);

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (this.playerOnly && !(sender instanceof org.bukkit.entity.Player) && this.onlyPlayerMsg != null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.onlyPlayerMsg));
            return true;
        }
        if (this.permission != null && !sender.hasPermission(this.permission) && this.noPermissionMsg != null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.noPermissionMsg));
            return true;
        }
        if (args.length > 0 && !CommandHandler.getSubCommands(this).isEmpty()) {
            List<Subcommand> subcommands = CommandHandler.getSubCommands(this);
            Subcommand subcommand = subcommands.stream().filter(sub -> sub.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null);
            if (subcommand == null) {
                if (this.noSubCommandFoundMsg != null)
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.noSubCommandFoundMsg));
                return true;
            }
            if (subcommand.isPlayerOnly() && !(sender instanceof org.bukkit.entity.Player) && this.onlyPlayerMsg != null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.onlyPlayerMsg));
                return true;
            }
            subcommand.execute(sender, Arrays.asList(args));
        } else {
            execute(sender, Arrays.asList(args));
        }
        return true;
    }
}
