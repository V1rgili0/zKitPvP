package it.danilotallaric.zkitpvp.data;

import it.danilotallaric.zkitpvp.KitPvP;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerData {
    private final UUID uuid;

    public long endCombatTimestamp;

    public long endEnderTimestamp;

    public List<Player> assisters = new ArrayList<>();

    public List<Player> deathsplayers = new ArrayList<>();

    public boolean isBuilder = false;

    public boolean inCombat = false;

    public boolean inEnderCooldown = false;

    public boolean atSpawn = true;

    public boolean pickupArrows = true;

    public boolean pickupGoldenApple = true;

    public int kills = 0;

    public int deaths = 0;

    public int streak = 0;

    private Player lastPlayer;

    private long bounty;

    private double balance;

    public void addBounty(long bounty) {
        this.bounty += bounty;
    }

    public long getBounty() {
        return this.bounty;
    }

    public void setBounty(long bounty) {
        this.bounty = bounty;
    }

    public Player getLastPlayer() {
        return this.lastPlayer;
    }

    public void setLastPlayer(Player lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        YamlConfiguration configuration = new YamlConfiguration();
        File file = new File(KitPvP.getInstance().getDataFolder().getAbsolutePath() + "/data", uuid.toString());
        if (file.exists())
            try {
                configuration.load(file);
                this.balance = configuration.getDouble("balance", 0.0D);
                this.kills = configuration.getInt("kills", 0);
                this.deaths = configuration.getInt("deaths", 0);
                this.streak = configuration.getInt("streak", 0);
                this.bounty = configuration.getLong("bounty", 0L);
                this.pickupArrows = configuration.getBoolean("pickup-arrows", true);
                this.pickupGoldenApple = configuration.getBoolean("pickup-apples", true);
            } catch (InvalidConfigurationException|java.io.IOException var5) {
                var5.printStackTrace();
            }
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (this.balance - amount < 0.0D) {
            this.balance = 0.0D;
        } else {
            this.balance -= amount;
        }
    }

    public double getKD() {
        if (this.deaths == 0)
            return this.kills;
        return (double) this.kills / this.deaths;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}
