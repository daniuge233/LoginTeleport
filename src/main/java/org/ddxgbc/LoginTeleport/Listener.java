package org.ddxgbc.LoginTeleport;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener implements org.bukkit.event.Listener {
    public Location loc = null;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        if (loc != null) {
            evt.getPlayer().teleport(loc);
        }
    }
}
