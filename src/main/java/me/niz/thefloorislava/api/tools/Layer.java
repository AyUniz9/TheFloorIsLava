package me.niz.thefloorislava.api.tools;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class Layer {

    private final int minX, maxX;
    private final int y;
    private final int minZ, maxZ;
    private final @NotNull World world;

    public Layer(int x1, int x2, int y, int z1, int z2, String world) {

        int[] xArray = this.order(x1, x2);
        int[] zArray = this.order(z1, z2);

        this.minX = xArray[0];
        this.minZ = zArray[0];

        this.maxX = xArray[1];
        this.maxZ = zArray[1];

        this.y = y;

        this.world = Bukkit.getWorld(world);
    }

    private int[] order(int v1, int v2) {
        return new int[]{Math.min(v1, v2), Math.max(v1, v2)};
    }

    /*
    public void addLava() {

        for (int x = this.minX; x <= this.maxX; x++) {
            for (int y = 0; y <= 5; y++) {
                for (int z = 0; z <= 5; z++) {
                    if (this.world.getBlockAt(x, y, z).getType() == Material.AIR)
                        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.LAVA);
                }
            }
        }
    }
    */

    // Idée de Sysy
    // Mettre cette méthode dans un utils ?
}
