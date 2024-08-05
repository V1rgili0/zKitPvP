package it.danilotallaric.zkitpvp.tasks;

import it.danilotallaric.zkitpvp.KitPvP;
import it.danilotallaric.zkitpvp.data.PlayerData;

public class GeneralTask implements Runnable {
    public static final double combatTimer = KitPvP.getFileManager().getConfig().getDouble("combat.timer");

    public static final double enderTimer = KitPvP.getFileManager().getConfig().getDouble("ender-pearl.timer");

    public void run() {
        if (!KitPvP.getDataManager().getAllData().isEmpty())
            for (int i = 0; i < KitPvP.getDataManager().getAllData().size(); i++) {
                PlayerData data = KitPvP.getDataManager().getAllData().get(i);
                if (data != null) {
                    long current = System.currentTimeMillis();
                    long combatTimestamp = data.endCombatTimestamp;
                    long combatDiff = combatTimestamp - current;
                    long enderTimestamp = data.endEnderTimestamp;
                    long enderDiff = enderTimestamp - current;
                    data.inCombat = (combatDiff > 0L);
                    data.inEnderCooldown = (enderDiff > 0L);
                    KitPvP.getDataManager().updateData(data);
                }
            }
    }
}
