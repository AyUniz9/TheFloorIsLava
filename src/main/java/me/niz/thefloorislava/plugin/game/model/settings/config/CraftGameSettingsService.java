package me.niz.thefloorislava.plugin.game.model.settings.config;

import me.niz.thefloorislava.api.game.model.settings.GameSettings;
import me.niz.thefloorislava.api.game.model.settings.config.GameSettingsDAO;
import me.niz.thefloorislava.api.game.model.settings.config.GameSettingsModel;
import me.niz.thefloorislava.api.game.model.settings.config.GameSettingsService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CraftGameSettingsService implements GameSettingsService {

    private final GameSettingsModel gameSettingsModel;
    private final GameSettingsDAO gameSettingsDAO;

    public CraftGameSettingsService(@NotNull GameSettingsModel gameSettingsModel, @NotNull GameSettingsDAO gameSettingsDAO) {
        this.gameSettingsModel = gameSettingsModel;
        this.gameSettingsDAO = gameSettingsDAO;
    }

    @Override
    public void loadGameSettings() {

        Optional<GameSettings> gameSettingsOptional = this.gameSettingsDAO.getGameSettings();

        if (!gameSettingsOptional.isPresent())
            throw new IllegalStateException("Cannot load Game Settings !");

        this.gameSettingsModel.setGameSettings(gameSettingsOptional.get());
    }

    @Override
    public void reload() {
        this.loadGameSettings();
    }
}
