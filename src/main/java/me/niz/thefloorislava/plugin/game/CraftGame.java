package me.niz.thefloorislava.plugin.game;

import me.niz.thefloorislava.api.game.Game;
import me.niz.thefloorislava.api.game.model.arena.Arena;
import org.jetbrains.annotations.NotNull;

public class CraftGame implements Game {

    private final Arena arena;

    public CraftGame(@NotNull Arena arena) {
        this.arena = arena;
    }

    @Override
    public Arena getArena() {
        return this.arena;
    }
}
