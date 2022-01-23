package me.niz.thefloorislava;

import me.niz.thefloorislava.plugin.configuration.FileHandler;
import me.niz.thefloorislava.plugin.player.data.PlayerDataManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class TheFloorIsLava extends JavaPlugin {

    private final FileHandler fileHandler = new FileHandler(this);
    private PlayerDataManager playerDataManager;

    @Override
    public void onEnable() {

        // Setting up player data module.
        try {
            playerDataManager = new PlayerDataManager(this);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public PlayerDataManager getPlayerDataManager() {
        return this.playerDataManager;
    }

    public FileHandler getFileHandler() {
        return this.fileHandler;
    }
}
