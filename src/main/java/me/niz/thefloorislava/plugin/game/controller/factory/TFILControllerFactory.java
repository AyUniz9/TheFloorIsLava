package me.niz.thefloorislava.plugin.game.controller.factory;

import me.niz.thefloorislava.api.game.controller.GameController;
import me.niz.thefloorislava.api.game.model.GameState;

public class TFILControllerFactory {

    public static GameController buildController(GameState gameState) {
        switch (gameState) {
            case WAITING:
                return null;
            case RUNNING:
                return null;
            case ENDING:
                return null;
            default:
                throw new IllegalArgumentException("");
        }
    }

}
