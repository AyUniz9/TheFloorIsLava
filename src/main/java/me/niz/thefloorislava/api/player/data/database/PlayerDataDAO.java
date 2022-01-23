package me.niz.thefloorislava.api.player.data.database;

import me.niz.thefloorislava.api.exception.SQLPlayerDataException;
import me.niz.thefloorislava.api.player.NPlayer;
import me.niz.thefloorislava.api.player.data.PlayerData;

import java.util.Optional;
import java.util.UUID;

public interface PlayerDataDAO {

    Optional<PlayerData> loadPlayer(UUID playerUUID) throws SQLPlayerDataException;

    void createPlayerData(PlayerData playerData) throws SQLPlayerDataException;

    void resetPlayerData(PlayerData playerData) throws SQLPlayerDataException;

    void updateNickname(NPlayer player) throws SQLPlayerDataException;

    void savePlayerData(PlayerData playerData) throws SQLPlayerDataException;

    boolean containsPlayerData(UUID playerUUID) throws SQLPlayerDataException;
}
