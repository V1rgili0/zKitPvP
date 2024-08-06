package it.danilotallaric.zkitpvp.commands.impl;

import it.danilotallaric.zkitpvp.KitPvP;
import it.danilotallaric.zkitpvp.commands.api.KitPvPCommand;
import it.danilotallaric.zkitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class DiscordCommand
        extends KitPvPCommand {
    public DiscordCommand() {
        super(KitPvP.getInstance(), "discord", "zkitpvp.commands.discord", false);
    }

    public boolean execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        player.sendMessage(ChatUtils.getFormattedText("discord.message"));
        return false;
    }
}
