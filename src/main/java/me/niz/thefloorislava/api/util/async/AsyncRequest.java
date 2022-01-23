package me.niz.thefloorislava.api.util.async;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class AsyncRequest {

    private final Plugin plugin;

    public AsyncRequest(@NotNull Plugin plugin) {
        this.plugin = plugin;
    }

    public void executeTask(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, runnable);
    }

}
