package me.niz.thefloorislava.api.player;

import me.niz.thefloorislava.plugin.player.GameMode;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface NPlayer {

    Optional<Player> getBukkitPlayer();

    UUID getUUID();
    String getName();

    boolean isPlaying();
    boolean isSpectating();

    GameMode getGameMode();

    void setPlaying(boolean playing);
    void setSpectating(boolean spectating);
    void setGameMode(GameMode gameMode);

}
