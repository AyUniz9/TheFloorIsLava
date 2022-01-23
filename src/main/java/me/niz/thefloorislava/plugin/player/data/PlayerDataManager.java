package me.niz.thefloorislava.plugin.player.data;

import me.niz.thefloorislava.TheFloorIsLava;
import me.niz.thefloorislava.api.player.data.database.PlayerDataDAO;
import me.niz.thefloorislava.api.player.data.database.PlayerDataModel;
import me.niz.thefloorislava.api.player.data.database.PlayerDataService;
import me.niz.thefloorislava.plugin.player.data.database.CraftPlayerDataModel;
import me.niz.thefloorislava.plugin.player.data.database.CraftPlayerDataService;
import me.niz.thefloorislava.plugin.player.data.database.SQLPlayerDataDAO;
import me.niz.thefloorislava.api.util.sql.DbConnection;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.logging.Level;

public class PlayerDataManager {

    private final TheFloorIsLava plugin;
    private PlayerDataService playerDataService;
    private PlayerDataModel playerDataModel;

    public PlayerDataManager(@NotNull TheFloorIsLava plugin) throws SQLException, ClassNotFoundException {
        this.plugin = plugin;

        this.setupPlayerDataService();
        this.registerListeners();
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerDataListener(this.playerDataService), this.plugin);
    }

    private void setupPlayerDataService() throws SQLException, ClassNotFoundException {
        DbConnection dbConnection = new DbConnection(this.plugin);
        dbConnection.open();

        PlayerDataDAO playerDataDAO = new SQLPlayerDataDAO(dbConnection);
        this.playerDataModel = new CraftPlayerDataModel();
        this.playerDataService = new CraftPlayerDataService(this.plugin, this.playerDataModel, playerDataDAO);

        this.playerDataService.loadOnlinePlayerData(bool -> {
            if (!bool) this.plugin.getLogger().log(Level.SEVERE, "Error while loading online player data !");
        });
    }

    public PlayerDataService getPlayerDataService() {
        return this.playerDataService;
    }

    public PlayerDataModel getPlayerDataModel() {
        return this.playerDataModel;
    }
}
