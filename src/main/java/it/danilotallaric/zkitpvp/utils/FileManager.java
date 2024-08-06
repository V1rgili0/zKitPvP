package it.danilotallaric.zkitpvp.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private final FileConfiguration config;

    private final FileConfiguration messages;

    private final Plugin instance;

    public FileManager(Plugin plugin) {
        this.instance = plugin;
        this.config = saveConfig("config.yml");
        this.messages = saveConfig("messages.yml");
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public FileConfiguration getMessages() {
        return this.messages;
    }

    public void saveFile(FileConfiguration configuration, File file) {
        try {
            configuration.save(file);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    private FileConfiguration saveConfig(String configName) {
        File file = new File(this.instance.getDataFolder(), configName);
        if (!file.exists())
            this.instance.saveResource(configName, false);
        return loadConfig(file);
    }

    public FileConfiguration loadConfig(File file) {
        YamlConfiguration configuration = new YamlConfiguration();
        try {
            configuration.load(file);
        } catch (InvalidConfigurationException | IOException var4) {
            var4.printStackTrace();
        }
        return configuration;
    }
}
