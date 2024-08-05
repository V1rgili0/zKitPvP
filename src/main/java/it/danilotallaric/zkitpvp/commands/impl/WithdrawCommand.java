package it.danilotallaric.zkitpvp.commands.impl;

import it.danilotallaric.zkitpvp.KitPvP;
import it.danilotallaric.zkitpvp.commands.api.KitPvPCommand;
import it.danilotallaric.zkitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class WithdrawCommand extends KitPvPCommand {
    public WithdrawCommand() {
        super(KitPvP.getInstance(), "BankNote", "zkitpvp.commands.BankNote", false);
    }

    public boolean execute(CommandSender sender, List<String> args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!args.isEmpty()) {
                String numero = args.get(0);


                if (!isInteger(numero) || Integer.parseInt(numero) <= 0) {
                    player.sendMessage(ChatUtils.getFormattedText("BankNote.invalid-number"));
                    return false;
                }

                int price = Integer.parseInt(numero);
                double balance = KitPvP.getEconomy().getBalance(player);

                if (balance < price) {
                    player.sendMessage(ChatUtils.getFormattedText("BankNote.not-enough-money"));
                    return false;
                }

                ItemStack banknote = KitPvP.createBanknote(player.getName(), Double.parseDouble(numero));

                KitPvP.getEconomy().withdrawPlayer(player, price);
                player.getInventory().addItem(banknote);
                player.sendMessage(ChatUtils.getFormattedText("BankNote.sent")
                        .replaceAll("%money%", numero));
                return true;
            } else {
                player.sendMessage(ChatUtils.getFormattedText("BankNote.usage"));
                return false;
            }
        } else {
            sender.sendMessage("This command can only be executed by a player.");
            return false;
        }
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
