package me.niz.thefloorislava.plugin.player;

import me.niz.thefloorislava.api.player.GamePlayer;
import org.bukkit.Bukkit;

import java.util.Optional;
import java.util.UUID;

public class CraftPlayer implements GamePlayer {

    private final UUID playerUUID;
    private final String playerName;
    private boolean isPlaying = false, isSpectating = false;
    private GameMode gameMode = GameMode.NONE;

    public CraftPlayer(UUID playerUUID, String playerName) {
        this.playerUUID = playerUUID;
        this.playerName = playerName;
    }

    public CraftPlayer(org.bukkit.entity.Player player) {
        this(player.getUniqueId(), player.getName());
    }


    @Override
    public Optional<org.bukkit.entity.Player> getBukkitPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(this.playerUUID));
    }

    @Override
    public UUID getUUID() {
        return this.playerUUID;
    }

    @Override
    public String getName() {
        return this.playerName;
    }

    @Override
    public boolean isPlaying() {
        return this.isPlaying;
    }

    @Override
    public boolean isSpectating() {
        return this.isSpectating;
    }

    @Override
    public GameMode getGameMode() {
        return this.gameMode;
    }

    @Override
    public void setPlaying(boolean playing) {
        if (this.isPlaying == playing)
            throw new IllegalStateException(String.format("CraftPlayer %s is already in the right playing state.", this.playerName));

        if (playing)
            this.gameMode = GameMode.PLAYING;
        this.isPlaying = playing;
    }

    @Override
    public void setSpectating(boolean spectating) {
        if (this.isPlaying == spectating)
            throw new IllegalStateException(String.format("CraftPlayer %s is already in the right spectating state.", this.playerName));

        if (spectating)
            this.gameMode = GameMode.SPECTATING;
        this.isSpectating = spectating;
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        if (this.gameMode == gameMode)
            throw new IllegalStateException(String.format("%s is already in gamemode %s", this.playerName, this.gameMode.name()));
    }
}
