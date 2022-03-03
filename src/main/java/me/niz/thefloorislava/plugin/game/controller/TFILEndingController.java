package me.niz.thefloorislava.plugin.game.controller;

import me.niz.thefloorislava.api.game.controller.GameController;
import me.niz.thefloorislava.api.game.model.GameState;

public class TFILEndingController implements GameController {

    public TFILEndingController() {

    }

    @Override
    public void load() {

    }

    @Override
    public void unload() {

    }

    @Override
    public GameState getState() {
        return GameState.ENDING;
    }
}
