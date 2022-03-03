package me.niz.thefloorislava.api.game.controller;

import me.niz.thefloorislava.api.game.model.GameState;

public interface GameController extends Controller {

    GameState getState();

}
