package it.danilotallaric.zkitpvp.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerDataManager {
    final List<PlayerData> playerDataList = new ArrayList<>();

    public List<PlayerData> getAllData() {
        return this.playerDataList;
    }

    public PlayerData getPlayerData(UUID uuid) {
        synchronized (this.playerDataList) {
            PlayerData playerData = this.playerDataList.stream().filter(x -> x.getUUID().equals(uuid)).findFirst().orElse(null);
            if (playerData == null) {
                this.playerDataList.add(new PlayerData(uuid));
                return getPlayerData(uuid);
            }
            return playerData;
        }
    }

    public void updateData(PlayerData data) {
        if (data != null)
            synchronized (this.playerDataList) {
                this.playerDataList.remove(getPlayerData(data.getUUID()));
                this.playerDataList.add(data);
            }
    }
}
