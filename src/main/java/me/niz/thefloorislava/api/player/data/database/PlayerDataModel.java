package me.niz.thefloorislava.api.player.data.database;

import me.niz.thefloorislava.api.player.data.PlayerData;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public interface PlayerDataModel {

    void addPlayerData(@NotNull PlayerData playerData);
    void removePlayerData(@NotNull UUID playerUUID);
    void replacePlayerData(@NotNull PlayerData playerData);

    Optional<PlayerData> getPlayerData(@NotNull UUID playerUUID);

    boolean containsPlayer(@NotNull UUID playerUUID);
    boolean containsPlayerData(@NotNull PlayerData playerData);

    void clearCache();

}
