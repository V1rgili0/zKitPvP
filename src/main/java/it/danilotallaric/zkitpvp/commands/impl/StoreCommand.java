
package it.danilotallaric.zkitpvp.commands.impl;

import it.danilotallaric.zkitpvp.KitPvP;
import it.danilotallaric.zkitpvp.commands.api.KitPvPCommand;
import it.danilotallaric.zkitpvp.utils.ChatUtils;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StoreCommand extends KitPvPCommand {

    public StoreCommand() {
        super(KitPvP.getInstance(), "store", "zkitpvp.commands.store", false);
    }

    public boolean execute(CommandSender sender, List<String> args) {
        Player player = (Player)sender;
        player.sendMessage(ChatUtils.getFormattedText("store.message"));
        return false;
    }
}
