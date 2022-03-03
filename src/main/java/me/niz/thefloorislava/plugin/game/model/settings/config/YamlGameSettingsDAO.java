package me.niz.thefloorislava.plugin.game.model.settings.config;

import me.niz.thefloorislava.api.game.model.settings.GameSettings;
import me.niz.thefloorislava.api.game.model.settings.config.GameSettingsDAO;
import me.niz.thefloorislava.plugin.configuration.FileHandler;
import me.niz.thefloorislava.plugin.game.model.settings.CraftGameSettings;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class YamlGameSettingsDAO implements GameSettingsDAO {

    private final FileHandler fileHandler;

    public YamlGameSettingsDAO(@NotNull FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public Optional<GameSettings> getGameSettings() {

        FileConfiguration mainConfig = this.fileHandler.getMainConfigFile().getConfig();

        int gameDuration = mainConfig.getInt("gameSettings.gameDuration");
        int lavaCooldown = mainConfig.getInt("gameSettings.lavaCooldown");
        int timeBeforeStart = mainConfig.getInt("gameSettings.timeBeforeStart");
        int maxPlayers = mainConfig.getInt("gameSettings.maxPlayers");
        int minPlayers = mainConfig.getInt("gameSettings.minPlayers");

        return Optional.of(new CraftGameSettings(gameDuration, lavaCooldown, timeBeforeStart, maxPlayers, minPlayers));
    }
}
