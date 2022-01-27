package me.niz.thefloorislava.plugin.game.settings.config;

import me.niz.thefloorislava.api.game.settings.GameSettings;
import me.niz.thefloorislava.api.game.settings.config.GameSettingsDAO;
import me.niz.thefloorislava.plugin.configuration.FileHandler;
import me.niz.thefloorislava.plugin.game.settings.CraftGameSettings;
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

        int gameDuration = mainConfig.getInt("gameDuration");
        int lavaCooldown = mainConfig.getInt("lavaCooldown");
        int timeBeforeStart = mainConfig.getInt("timeBeforeStart");
        int maxPlayers = mainConfig.getInt("maxPlayers");
        int minPlayers = mainConfig.getInt("minPlayers");

        return Optional.of(new CraftGameSettings(gameDuration, lavaCooldown, timeBeforeStart, maxPlayers, minPlayers));
    }
}
