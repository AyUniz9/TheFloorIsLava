package me.niz.thefloorislava.api.game.model;

import java.util.Optional;

public enum GameState {

    WAITING, RUNNING, ENDING;

    public static Optional<GameState> getNext(GameState gameState) {

        if (gameState == null)
            throw new IllegalArgumentException("GameState cannot be null.");

        GameState[] states = GameState.values();
        int ordinal = gameState.ordinal();

        GameState next = ordinal + 1 < states.length ? states[ordinal + 1] : null;

        return Optional.ofNullable(next);
    }

    public static boolean asNext(GameState gameState) {
        if (gameState == null)
            throw new IllegalArgumentException("GameState cannot be null.");

        GameState[] states = GameState.values();
        int ordinal = gameState.ordinal();

        return ordinal + 1 < states.length;
    }

}
