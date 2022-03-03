package me.niz.thefloorislava.api.player.data.database;

import me.niz.thefloorislava.api.player.GamePlayer;
import me.niz.thefloorislava.api.player.data.GamePlayerData;
import me.niz.thefloorislava.api.tools.async.AsyncCallback;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface GamePlayerDataService {

    void loadPlayer(Player player);
    void loadOnlinePlayerData(AsyncCallback<Boolean> asyncCallback);
    void unloadPlayer(UUID playerUUID);

    void createPlayerData(GamePlayer nPlayer);

    void resetPlayer(GamePlayer nPlayer);

    Optional<GamePlayerData> getOnlinePlayerData(GamePlayer nPlayer);

    void getPlayerData(GamePlayer nPlayer, AsyncCallback<Optional<GamePlayerData>> asyncCallback);

    void containsPlayer(UUID playerUUID, AsyncCallback<Boolean> asyncCallback);

}
