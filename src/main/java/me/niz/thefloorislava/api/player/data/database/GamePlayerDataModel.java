package me.niz.thefloorislava.api.player.data.database;

import me.niz.thefloorislava.api.player.data.GamePlayerData;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public interface GamePlayerDataModel {

    void addPlayerData(@NotNull GamePlayerData gamePlayerData);
    void removePlayerData(@NotNull UUID playerUUID);
    void replacePlayerData(@NotNull GamePlayerData gamePlayerData);

    Optional<GamePlayerData> getPlayerData(@NotNull UUID playerUUID);

    boolean containsPlayer(@NotNull UUID playerUUID);
    boolean containsPlayerData(@NotNull GamePlayerData gamePlayerData);

    void clearCache();

}
