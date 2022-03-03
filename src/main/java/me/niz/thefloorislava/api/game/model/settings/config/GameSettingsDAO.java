package me.niz.thefloorislava.api.game.model.settings.config;

import me.niz.thefloorislava.api.game.model.settings.GameSettings;

import java.util.Optional;

public interface GameSettingsDAO {

    Optional<GameSettings> getGameSettings();

    /*
        int getSecondTime();
    int getLavaTime();
    int getTimeBeforeStart();
    int getMaxPlayers();
    int getMinPlayers();
     */
}
