package me.niz.thefloorislava.plugin.player.data.database;

import me.niz.thefloorislava.api.player.data.PlayerData;
import me.niz.thefloorislava.api.player.data.database.PlayerDataModel;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CraftPlayerDataModel implements PlayerDataModel {

    private final HashMap<UUID, PlayerData> playerDataMap = new HashMap<>();

    @Override
    public void addPlayerData(@NotNull PlayerData playerData) {
        if (this.playerDataMap.containsValue(playerData))
            throw new IllegalStateException(String.format("Player data for player %s already exist.", playerData.getNPlayer().getName()));

        this.playerDataMap.put(playerData.getNPlayer().getUUID(), playerData);
    }

    @Override
    public void removePlayerData(@NotNull UUID playerUUID) {
        if (!this.containsPlayer(playerUUID))
            throw new IllegalStateException(String.format("Player data for player doesn't exist. UUID: %s", playerUUID));

        this.playerDataMap.remove(playerUUID);
    }

    @Override
    public void replacePlayerData(@NotNull PlayerData playerData) {
        if (!this.containsPlayerData(playerData))
            throw new IllegalStateException(String.format("Player data for player %s doesn't exist.", playerData.getNPlayer().getName()));

        this.playerDataMap.replace(playerData.getNPlayer().getUUID(), playerData);
    }

    @Override
    public Optional<PlayerData> getPlayerData(@NotNull UUID playerUUID) {
        return Optional.ofNullable(this.playerDataMap.get(playerUUID));
    }

    @Override
    public boolean containsPlayer(@NotNull UUID playerUUID) {
        return this.playerDataMap.containsKey(playerUUID);
    }

    @Override
    public boolean containsPlayerData(@NotNull PlayerData playerData) {
        return this.playerDataMap.containsValue(playerData);
    }

    @Override
    public void clearCache() {
        this.playerDataMap.clear();
    }
}
