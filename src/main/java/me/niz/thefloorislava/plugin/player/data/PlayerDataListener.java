package me.niz.thefloorislava.plugin.player.data;

import me.niz.thefloorislava.api.player.data.database.GamePlayerDataService;
import me.niz.thefloorislava.plugin.player.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDataListener implements Listener {

    private final GamePlayerDataService gamePlayerDataService;

    public PlayerDataListener(@NotNull GamePlayerDataService gamePlayerDataService) {
        this.gamePlayerDataService = gamePlayerDataService;
    }

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        this.gamePlayerDataService.containsPlayer(player.getUniqueId(), boolCallback -> {

            if (!boolCallback)
                this.gamePlayerDataService.createPlayerData(new CraftPlayer(player.getUniqueId(), player.getName()));
            else
                this.gamePlayerDataService.loadPlayer(player);
        });

    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        this.gamePlayerDataService.unloadPlayer(event.getPlayer().getUniqueId());
    }

}
