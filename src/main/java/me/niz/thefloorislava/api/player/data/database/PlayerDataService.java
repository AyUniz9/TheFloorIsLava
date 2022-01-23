package me.niz.thefloorislava.api.player.data.database;

import me.niz.thefloorislava.api.player.NPlayer;
import me.niz.thefloorislava.api.player.data.PlayerData;
import me.niz.thefloorislava.api.util.async.AsyncCallback;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerDataService {

    void loadPlayer(Player player);
    void loadOnlinePlayerData(AsyncCallback<Boolean> asyncCallback);
    void unloadPlayer(UUID playerUUID);

    void createPlayerData(NPlayer nPlayer);

    void resetPlayer(NPlayer nPlayer);

    Optional<PlayerData> getOnlinePlayerData(NPlayer nPlayer);

    void getPlayerData(NPlayer nPlayer, AsyncCallback<Optional<PlayerData>> asyncCallback);

    void containsPlayer(UUID playerUUID, AsyncCallback<Boolean> asyncCallback);

}
