package me.niz.thefloorislava.plugin.game.model;

import me.niz.thefloorislava.api.game.model.arena.Arena;
import me.niz.thefloorislava.api.game.model.settings.GameSettings;
import me.niz.thefloorislava.api.player.GamePlayer;
import me.niz.thefloorislava.api.tools.others.Observable;
import org.bukkit.block.data.type.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TheFloorIsLavaModel implements Observable {

    private final List<Observer> observerList = new ArrayList<>();
    private final Map<UUID, GamePlayer> gamePlayerList = new HashMap<>();
    private final GameSettings gameSettings;
    private final Arena arena;

    public TheFloorIsLavaModel(@NotNull GameSettings gameSettings, @NotNull Arena arena) {
        this.gameSettings = gameSettings;
        this.arena = arena;
    }

    public void addPlayer() {

    }

    @Override
    public void notifyObservers() {
        this.observerList.forEach(Object::notify);
    }
}
