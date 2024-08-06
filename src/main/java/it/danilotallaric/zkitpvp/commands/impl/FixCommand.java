package it.danilotallaric.zkitpvp.commands.impl;

import it.danilotallaric.zkitpvp.KitPvP;
import it.danilotallaric.zkitpvp.commands.api.KitPvPCommand;
import it.danilotallaric.zkitpvp.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public class FixCommand extends KitPvPCommand {
    public FixCommand() {
        super(KitPvP.getInstance(), "fix", "kitpvp.commands.fix", true);
    }

    private static boolean isHasBrokenItems(PlayerInventory inventory) {
        ItemStack[] contents = inventory.getContents();
        ItemStack[] armorContents = inventory.getArmorContents();
        boolean hasBrokenItems = false;
        ItemStack[] var11 = contents;
        int var12 = contents.length;
        int var13;
        for (var13 = 0; var13 < var12; var13++) {
            ItemStack item = var11[var13];
            if (item != null && !item.getType().isBlock() && item.getDurability() > 0) {
                Material itemType = item.getType();
                if (itemType != Material.GOLDEN_APPLE && itemType != Material.POTION) {
                    item.setDurability((short) 0);
                    hasBrokenItems = true;
                }
            }
        }
        var11 = armorContents;
        var12 = armorContents.length;
        for (var13 = 0; var13 < var12; var13++) {
            ItemStack item = var11[var13];
            if (item != null && item.getDurability() > 0) {
                item.setDurability((short) 0);
                hasBrokenItems = true;
            }
        }
        return hasBrokenItems;
    }

    public boolean execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        int price = KitPvP.getFileManager().getConfig().getInt("fix.cost", 0);
        double balance = KitPvP.getEconomy().getBalance(player);
        if (balance < price) {
            player.sendMessage(ChatUtils.getFormattedText("fix.not-enough-money"));
            return false;
        }
        PlayerInventory inventory = player.getInventory();
        boolean hasBrokenItems = isHasBrokenItems(inventory);
        if (!hasBrokenItems) {
            player.sendMessage(ChatUtils.getFormattedText("fix.no-broken-items"));
            return false;
        }
        KitPvP.getEconomy().withdrawPlayer(player, price);
        player.sendMessage(ChatUtils.getFormattedText("fix.repaired"));
        return true;
    }
}
