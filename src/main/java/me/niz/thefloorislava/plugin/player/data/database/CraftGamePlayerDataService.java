package me.niz.thefloorislava.plugin.player.data.database;

import me.niz.thefloorislava.TheFloorIsLava;
import me.niz.thefloorislava.api.player.data.GamePlayerData;
import me.niz.thefloorislava.api.player.data.database.GamePlayerDataDAO;
import me.niz.thefloorislava.api.player.data.database.GamePlayerDataModel;
import me.niz.thefloorislava.api.player.data.database.GamePlayerDataService;
import me.niz.thefloorislava.api.exception.SQLPlayerDataException;
import me.niz.thefloorislava.api.player.GamePlayer;
import me.niz.thefloorislava.api.tools.async.AsyncCallback;
import me.niz.thefloorislava.api.tools.async.AsyncRequest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class CraftGamePlayerDataService implements GamePlayerDataService {

    private final TheFloorIsLava plugin;
    private final GamePlayerDataModel gamePlayerDataModel;
    private final GamePlayerDataDAO gamePlayerDataDAO;

    public CraftGamePlayerDataService(@NotNull TheFloorIsLava plugin, @NotNull GamePlayerDataModel gamePlayerDataModel, @NotNull GamePlayerDataDAO gamePlayerDataDAO) {
        this.plugin = plugin;
        this.gamePlayerDataModel = gamePlayerDataModel;
        this.gamePlayerDataDAO = gamePlayerDataDAO;
    }

    @Override
    public void loadPlayer(Player player) {
        new AsyncRequest(this.plugin).executeTask(() -> {
            try {
                Optional<GamePlayerData> optionalPlayerData = this.gamePlayerDataDAO.loadPlayer(player.getUniqueId());

                if (!optionalPlayerData.isPresent())
                    throw new IllegalStateException(String.format("CraftPlayer %s doesn't exist.", player.getName()));

                this.gamePlayerDataModel.addPlayerData(optionalPlayerData.get());
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
        if (!this.gamePlayerDataModel.containsPlayer(playerUUID))
            throw new IllegalStateException(String.format("Can't unload player data because player data is not loaded. UUID: %s", playerUUID));

        this.gamePlayerDataModel.removePlayerData(playerUUID);
    }

    @Override
    public void createPlayerData(GamePlayer nPlayer) {
        new AsyncRequest(this.plugin).executeTask(() -> {
            try {
                GamePlayerData gamePlayerData = new me.niz.thefloorislava.plugin.player.data.GamePlayerData(nPlayer);

                this.gamePlayerDataDAO.createPlayerData(gamePlayerData);
                this.gamePlayerDataModel.addPlayerData(gamePlayerData);
            } catch (SQLPlayerDataException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void resetPlayer(GamePlayer nPlayer) {
        new AsyncRequest(this.plugin).executeTask(() -> {
            try {
                GamePlayerData gamePlayerData = new me.niz.thefloorislava.plugin.player.data.GamePlayerData(nPlayer);

                this.gamePlayerDataDAO.resetPlayerData(gamePlayerData);
                this.gamePlayerDataModel.replacePlayerData(gamePlayerData);
            } catch (SQLPlayerDataException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public Optional<GamePlayerData> getOnlinePlayerData(GamePlayer nPlayer) {
        return Optional.of(this.gamePlayerDataModel.getPlayerData(nPlayer.getUUID()).get());
    }

    @Override
    public void getPlayerData(GamePlayer nPlayer, AsyncCallback<Optional<GamePlayerData>> asyncCallback) {
        Optional<GamePlayerData> optionalModelPlayerData = this.gamePlayerDataModel.getPlayerData(nPlayer.getUUID());

        if (optionalModelPlayerData.isPresent()) {
            asyncCallback.onResponse(optionalModelPlayerData);
            return;
        }

        new AsyncRequest(this.plugin).executeTask(() -> {
            try {
                Optional<GamePlayerData> optionalDbPlayerData = this.gamePlayerDataDAO.loadPlayer(nPlayer.getUUID());

                this.runSync(() -> asyncCallback.onResponse(optionalDbPlayerData));
            } catch (SQLPlayerDataException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void containsPlayer(UUID playerUUID, AsyncCallback<Boolean> asyncCallback) {
        if (this.gamePlayerDataModel.containsPlayer(playerUUID)) {
            asyncCallback.onResponse(true);
            return;
        }

        new AsyncRequest(this.plugin).executeTask(() -> {
            try {
                if (this.gamePlayerDataDAO.containsPlayerData(playerUUID))
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
