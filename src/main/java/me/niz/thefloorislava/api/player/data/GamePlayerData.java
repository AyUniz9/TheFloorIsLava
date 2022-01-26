package me.niz.thefloorislava.api.player.data;

import me.niz.thefloorislava.api.player.GamePlayer;

public interface GamePlayerData {

    GamePlayer getNPlayer();

    int getPlayedGames();
    int getWonGames();

    void reset();

    void incrementPlayedGames();
    void incrementWonGames();
}
