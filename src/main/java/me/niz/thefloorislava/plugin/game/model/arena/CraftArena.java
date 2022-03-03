package me.niz.thefloorislava.plugin.game.model.arena;

import me.niz.thefloorislava.api.tools.Cuboid;
import me.niz.thefloorislava.api.tools.Layer;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class CraftArena {

    private final Cuboid cuboid;
    private final Location spawn;
    private Layer actualLayer;

    public CraftArena(@NotNull Cuboid cuboid, @NotNull Location spawn) {
        this.cuboid = cuboid;
        this.spawn = spawn;
    }

    @NotNull
    public Cuboid getZone() {
        return this.cuboid;
    }

    @NotNull
    public Location getSpawn() {
        return this.spawn;
    }
}