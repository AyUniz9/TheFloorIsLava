package me.niz.thefloorislava.api.player.data;

import me.niz.thefloorislava.api.player.NPlayer;

public interface PlayerData {

    NPlayer getNPlayer();

    int getPlayedGames();
    int getWonGames();

    void reset();

    void incrementPlayedGames();
    void incrementWonGames();
}
