package it.danilotallaric.zkitpvp.inventory;

import java.util.HashMap;
import java.util.function.Consumer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {
    public static HashMap<Inventory, HashMap<Integer, Consumer<InventoryClickEvent>>> actions = new HashMap<>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        HashMap<Integer, Consumer<InventoryClickEvent>> actionList = actions.get(event.getInventory());
        if (actionList != null && !actionList.isEmpty())
            actionList.forEach((key, value) -> {
                if (key == event.getSlot())
                    value.accept(event);
            });
    }
}
