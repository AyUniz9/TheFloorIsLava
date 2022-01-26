package me.niz.thefloorislava.plugin.player.data.database;

import me.niz.thefloorislava.api.player.data.GamePlayerData;
import me.niz.thefloorislava.api.player.data.database.GamePlayerDataModel;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CraftGamePlayerDataModel implements GamePlayerDataModel {

    private final HashMap<UUID, GamePlayerData> playerDataMap = new HashMap<>();

    @Override
    public void addPlayerData(@NotNull GamePlayerData gamePlayerData) {
        if (this.playerDataMap.containsValue(gamePlayerData))
            throw new IllegalStateException(String.format("CraftPlayer data for player %s already exist.", gamePlayerData.getNPlayer().getName()));

        this.playerDataMap.put(gamePlayerData.getNPlayer().getUUID(), gamePlayerData);
    }

    @Override
    public void removePlayerData(@NotNull UUID playerUUID) {
        if (!this.containsPlayer(playerUUID))
            throw new IllegalStateException(String.format("CraftPlayer data for player doesn't exist. UUID: %s", playerUUID));

        this.playerDataMap.remove(playerUUID);
    }

    @Override
    public void replacePlayerData(@NotNull GamePlayerData gamePlayerData) {
        if (!this.containsPlayerData(gamePlayerData))
            throw new IllegalStateException(String.format("CraftPlayer data for player %s doesn't exist.", gamePlayerData.getNPlayer().getName()));

        this.playerDataMap.replace(gamePlayerData.getNPlayer().getUUID(), gamePlayerData);
    }

    @Override
    public Optional<GamePlayerData> getPlayerData(@NotNull UUID playerUUID) {
        return Optional.ofNullable(this.playerDataMap.get(playerUUID));
    }

    @Override
    public boolean containsPlayer(@NotNull UUID playerUUID) {
        return this.playerDataMap.containsKey(playerUUID);
    }

    @Override
    public boolean containsPlayerData(@NotNull GamePlayerData gamePlayerData) {
        return this.playerDataMap.containsValue(gamePlayerData);
    }

    @Override
    public void clearCache() {
        this.playerDataMap.clear();
    }
}
