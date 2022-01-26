package me.niz.thefloorislava.api.player.data.database;

import me.niz.thefloorislava.api.exception.SQLPlayerDataException;
import me.niz.thefloorislava.api.player.GamePlayer;
import me.niz.thefloorislava.api.player.data.GamePlayerData;

import java.util.Optional;
import java.util.UUID;

public interface GamePlayerDataDAO {

    Optional<GamePlayerData> loadPlayer(UUID playerUUID) throws SQLPlayerDataException;

    void createPlayerData(GamePlayerData gamePlayerData) throws SQLPlayerDataException;

    void resetPlayerData(GamePlayerData gamePlayerData) throws SQLPlayerDataException;

    void updateNickname(GamePlayer player) throws SQLPlayerDataException;

    void savePlayerData(GamePlayerData gamePlayerData) throws SQLPlayerDataException;

    boolean containsPlayerData(UUID playerUUID) throws SQLPlayerDataException;
}
