package me.niz.thefloorislava.plugin.player.data;

import me.niz.thefloorislava.TheFloorIsLava;
import me.niz.thefloorislava.api.player.data.database.GamePlayerDataDAO;
import me.niz.thefloorislava.api.player.data.database.GamePlayerDataModel;
import me.niz.thefloorislava.api.player.data.database.GamePlayerDataService;
import me.niz.thefloorislava.plugin.player.data.database.CraftGamePlayerDataModel;
import me.niz.thefloorislava.plugin.player.data.database.CraftGamePlayerDataService;
import me.niz.thefloorislava.plugin.player.data.database.SQLGamePlayerDataDAO;
import me.niz.thefloorislava.api.util.sql.DbConnection;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.logging.Level;

public class PlayerDataManager {

    private final TheFloorIsLava plugin;
    private GamePlayerDataService gamePlayerDataService;
    private GamePlayerDataModel gamePlayerDataModel;

    public PlayerDataManager(@NotNull TheFloorIsLava plugin) throws SQLException, ClassNotFoundException {
        this.plugin = plugin;

        this.setupPlayerDataService();
        this.registerListeners();
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerDataListener(this.gamePlayerDataService), this.plugin);
    }

    private void setupPlayerDataService() throws SQLException, ClassNotFoundException {
        DbConnection dbConnection = new DbConnection(this.plugin);
        dbConnection.open();

        GamePlayerDataDAO gamePlayerDataDAO = new SQLGamePlayerDataDAO(dbConnection);
        this.gamePlayerDataModel = new CraftGamePlayerDataModel();
        this.gamePlayerDataService = new CraftGamePlayerDataService(this.plugin, this.gamePlayerDataModel, gamePlayerDataDAO);

        this.gamePlayerDataService.loadOnlinePlayerData(bool -> {
            if (!bool) this.plugin.getLogger().log(Level.SEVERE, "Error while loading online player data !");
        });
    }

    public GamePlayerDataService getPlayerDataService() {
        return this.gamePlayerDataService;
    }

    public GamePlayerDataModel getPlayerDataModel() {
        return this.gamePlayerDataModel;
    }
}
