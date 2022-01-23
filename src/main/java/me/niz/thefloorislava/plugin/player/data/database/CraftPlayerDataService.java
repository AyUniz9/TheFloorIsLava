package me.niz.thefloorislava.plugin.player.data.database;

import me.niz.thefloorislava.TheFloorIsLava;
import me.niz.thefloorislava.api.player.data.database.PlayerDataDAO;
import me.niz.thefloorislava.api.player.data.database.PlayerDataModel;
import me.niz.thefloorislava.api.player.data.database.PlayerDataService;
import me.niz.thefloorislava.api.exception.SQLPlayerDataException;
import me.niz.thefloorislava.api.player.NPlayer;
import me.niz.thefloorislava.api.player.data.PlayerData;
import me.niz.thefloorislava.plugin.player.data.CraftPlayerData;
import me.niz.thefloorislava.api.util.async.AsyncCallback;
import me.niz.thefloorislava.api.util.async.AsyncRequest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class CraftPlayerDataService implements PlayerDataService {

    private final TheFloorIsLava plugin;
    private final PlayerDataModel playerDataModel;
    private final PlayerDataDAO playerDataDAO;

    public CraftPlayerDataService(@NotNull TheFloorIsLava plugin, @NotNull PlayerDataModel playerDataModel, @NotNull PlayerDataDAO playerDataDAO) {
        this.plugin = plugin;
        this.playerDataModel = playerDataModel;
        this.playerDataDAO = playerDataDAO;
    }

    @Override
    public void loadPlayer(Player player) {
        new AsyncRequest(this.plugin).executeTask(() -> {
            try {
                Optional<PlayerData> optionalPlayerData = this.playerDataDAO.loadPlayer(player.getUniqueId());

                if (!optionalPlayerData.isPresent())
                    throw new IllegalStateException(String.format("Player %s doesn't exist.", player.getName()));

                this.playerDataModel.addPlayerData(optionalPlayerData.get());
            } catch (SQLPlayerDataException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void loadOnlinePlayerData(AsyncCallback<Boolean> asyncCallback) {
        new AsyncRequest(this.plugin).executeTask(() -> Bukkit.getOnlinePlayers().forEach(this::loadPlayer));
    }

    @Override
    public void unloadPlayer(UUID playerUUID) {
        if (!this.playerDataModel.containsPlayer(playerUUID))
            throw new IllegalStateException(String.format("Can't unload player data because player data is not loaded. UUID: %s", playerUUID));

        this.playerDataModel.removePlayerData(playerUUID);
    }

    @Override
    public void createPlayerData(NPlayer nPlayer) {
        new AsyncRequest(this.plugin).executeTask(() -> {
            try {
                PlayerData playerData = new CraftPlayerData(nPlayer);

                this.playerDataDAO.createPlayerData(playerData);
                this.playerDataModel.addPlayerData(playerData);
            } catch (SQLPlayerDataException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void resetPlayer(NPlayer nPlayer) {
        new AsyncRequest(this.plugin).executeTask(() -> {
            try {
                PlayerData playerData = new CraftPlayerData(nPlayer);

                this.playerDataDAO.resetPlayerData(playerData);
                this.playerDataModel.replacePlayerData(playerData);
            } catch (SQLPlayerDataException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public Optional<PlayerData> getOnlinePlayerData(NPlayer nPlayer) {
        return Optional.of(this.playerDataModel.getPlayerData(nPlayer.getUUID()).get());
    }

    @Override
    public void getPlayerData(NPlayer nPlayer, AsyncCallback<Optional<PlayerData>> asyncCallback) {
        Optional<PlayerData> optionalModelPlayerData = this.playerDataModel.getPlayerData(nPlayer.getUUID());

        if (optionalModelPlayerData.isPresent()) {
            asyncCallback.onResponse(optionalModelPlayerData);
            return;
        }

        new AsyncRequest(this.plugin).executeTask(() -> {
            try {
                Optional<PlayerData> optionalDbPlayerData = this.playerDataDAO.loadPlayer(nPlayer.getUUID());

                this.runSync(() -> asyncCallback.onResponse(optionalDbPlayerData));
            } catch (SQLPlayerDataException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void containsPlayer(UUID playerUUID, AsyncCallback<Boolean> asyncCallback) {
        if (this.playerDataModel.containsPlayer(playerUUID)) {
            asyncCallback.onResponse(true);
            return;
        }

        new AsyncRequest(this.plugin).executeTask(() -> {
            try {
                if (this.playerDataDAO.containsPlayerData(playerUUID))
                    this.runSync(() -> asyncCallback.onResponse(true));
                else
                    this.runSync(() -> asyncCallback.onResponse(false));
            } catch (SQLPlayerDataException e) {
                e.printStackTrace();
            }
        });
    }

    private void runSync(@NotNull Runnable runnable) {
        Bukkit.getScheduler().runTask(this.plugin, runnable);
    }
}
