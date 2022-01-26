package me.niz.thefloorislava.api.game.settings.config;

import me.niz.thefloorislava.api.game.settings.GameSettings;

public interface GameSettingsModel {

    void setGameSettings(GameSettings gameSettings);

    GameSettings getGameSettings();
}
