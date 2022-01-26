package me.niz.thefloorislava.plugin.game.settings.config;

import me.niz.thefloorislava.api.game.settings.GameSettings;
import me.niz.thefloorislava.api.game.settings.config.GameSettingsDAO;
import me.niz.thefloorislava.plugin.configuration.FileHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class YamlGameSettingsDAO implements GameSettingsDAO {

    private final FileHandler fileHandler;

    public YamlGameSettingsDAO(@NotNull FileHandler fileHandler){
        this.fileHandler = fileHandler;
    }

    @Override
    public Optional<GameSettings> getGameSettings() {

        FileConfiguration mainConfig = this.fileHandler.getMainConfigFile().getConfig();

        return Optional.empty();
    }
}
