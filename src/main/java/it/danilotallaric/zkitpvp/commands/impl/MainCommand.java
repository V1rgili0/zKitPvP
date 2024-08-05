package it.danilotallaric.zkitpvp.commands.impl;

import it.danilotallaric.zkitpvp.KitPvP;
import it.danilotallaric.zkitpvp.commands.api.CommandHandler;
import it.danilotallaric.zkitpvp.commands.api.KitPvPCommand;
import it.danilotallaric.zkitpvp.commands.impl.subcommands.BuildCommand;
import it.danilotallaric.zkitpvp.commands.impl.subcommands.ReloadCommand;
import it.danilotallaric.zkitpvp.commands.impl.subcommands.SetBountyCommand;
import it.danilotallaric.zkitpvp.commands.impl.subcommands.SetSpawnCommand;
import it.danilotallaric.zkitpvp.commands.impl.subcommands.SetStatsCommand;
import it.danilotallaric.zkitpvp.utils.ChatUtils;
import java.util.List;
import org.bukkit.command.CommandSender;

public class MainCommand extends KitPvPCommand {
    public MainCommand() {
        super(KitPvP.getInstance(), "kitpvp", "kitpvp.commands.admin", false);
        setNoSubCommandFoundMessage(ChatUtils.getFormattedText("admin.no-sub-command-found"));
        setNoPermissionMessage(ChatUtils.getColoredText("Unknown command. Type \"help\" for help."));
        CommandHandler.addSubCommand(this, new SetStatsCommand());
        CommandHandler.addSubCommand(this, new SetSpawnCommand());
        CommandHandler.addSubCommand(this, new SetBountyCommand());
        CommandHandler.addSubCommand(this, new BuildCommand());
        CommandHandler.addSubCommand(this, new ReloadCommand());
    }

    public boolean execute(CommandSender sender, List<String> args) {
        if (args.isEmpty())
            KitPvP.getFileManager().getMessages().getStringList("admin.help-command").forEach(msg -> sender.sendMessage(ChatUtils.getColoredText(msg)));
        return false;
    }
}
