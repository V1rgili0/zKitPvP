package it.danilotallaric.zkitpvp.commands.api;

import java.util.List;
import org.bukkit.command.CommandSender;

public abstract class Subcommand {
    private final String name;

    private final String permission;

    private final String parent;

    private final boolean playerOnly;

    public Subcommand(String parent, String name, String permission, boolean playerOnly) {
        this.parent = parent;
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

    public String getParentCommand() {
        return this.parent;
    }

    public boolean isPlayerOnly() {
        return this.playerOnly;
    }

    public abstract void execute(CommandSender paramCommandSender, List<String> paramList);
}
