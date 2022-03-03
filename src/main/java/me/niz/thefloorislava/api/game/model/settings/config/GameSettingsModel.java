package me.niz.thefloorislava.api.game.model.settings.config;

import me.niz.thefloorislava.api.game.model.settings.GameSettings;

public interface GameSettingsModel {

    void setGameSettings(GameSettings gameSettings);

    GameSettings getGameSettings();
}
