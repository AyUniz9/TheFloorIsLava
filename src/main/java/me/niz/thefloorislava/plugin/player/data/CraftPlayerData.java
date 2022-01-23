package me.niz.thefloorislava.plugin.player.data;

import me.niz.thefloorislava.api.player.NPlayer;
import me.niz.thefloorislava.api.player.data.PlayerData;
import org.jetbrains.annotations.NotNull;

public class CraftPlayerData implements PlayerData {


    private final NPlayer nPlayer;
    private int playedGames;
    private int wonGames;

    public CraftPlayerData(@NotNull NPlayer nPlayer, int playedGames, int wonGames) {
        this.nPlayer = nPlayer;
        this.playedGames = playedGames;
        this.wonGames = wonGames;
    }

    public CraftPlayerData(@NotNull NPlayer nPlayer) {
        this(nPlayer, 0, 0);
    }


    @Override
    public NPlayer getNPlayer() {
        return this.nPlayer;
    }

    @Override
    public int getPlayedGames() {
        return this.playedGames;
    }

    @Override
    public int getWonGames() {
        return this.wonGames;
    }

    @Override
    public void reset() {
        if (this.playedGames == 0)
            throw new IllegalStateException("Player's data cannot be reset ! Played game is already zero.");

        this.playedGames = 0;
        this.wonGames = 0;
    }

    @Override
    public void incrementPlayedGames() {
        this.playedGames += 1;
    }

    @Override
    public void incrementWonGames() {
        if(this.playedGames == this.wonGames)
            throw new IllegalStateException("Player ");
        this.wonGames += 1;
    }
}
