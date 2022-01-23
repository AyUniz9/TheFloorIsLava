package me.niz.thefloorislava.plugin.player.data;

import me.niz.thefloorislava.api.player.data.database.PlayerDataService;
import me.niz.thefloorislava.plugin.player.CraftNPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDataListener implements Listener {

    private final PlayerDataService playerDataService;

    public PlayerDataListener(@NotNull PlayerDataService playerDataService) {
        this.playerDataService = playerDataService;
    }

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.playerDataService.containsPlayer(player.getUniqueId(), boolCallback -> {

            if (!boolCallback)
                this.playerDataService.createPlayerData(new CraftNPlayer(player.getUniqueId(), player.getName()));
            else
                this.playerDataService.loadPlayer(player);
        });

    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        this.playerDataService.unloadPlayer(event.getPlayer().getUniqueId());
    }

}
