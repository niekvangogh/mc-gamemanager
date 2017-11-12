package com.nivyox.gamemanager.listeners;

import com.nivyox.gamemanager.GameManager;
import com.nivyox.gamemanager.games.Game;
import com.nivyox.gamemanager.games.RemoveReason;
import com.nivyox.gamemanager.games.events.GamePlayerDamageEvent;
import com.nivyox.gamemanager.games.events.GamePlayerDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void playerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Game currentGame = GameManager.getGame(player);
        if (currentGame != null) {
            event.getRecipients().clear();
            currentGame.getPlayers().forEach(gamePlayer -> event.getRecipients().add(gamePlayer));
        }
    }

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Game game = GameManager.getGame(player);
        if (game != null) {
            game.removePlayer(player, RemoveReason.DISCONNECT);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player killed = event.getEntity();
        Game game = GameManager.getGame(killed);
        if (game != null) {
            Bukkit.getPluginManager().callEvent(new GamePlayerDeathEvent(game, killed, event));
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getEntity() instanceof Player) {
                Player entity = (Player) event.getEntity();
                Player damager = (Player) event.getDamager();
                if (GameManager.getGame(entity) != null && GameManager.getGame(damager) != null) {
                    if (GameManager.getGame(entity) == GameManager.getGame(damager)) {
                        GamePlayerDamageEvent damageEvent = new GamePlayerDamageEvent(event, GameManager.getGame(entity));
                        Bukkit.getPluginManager().callEvent(damageEvent);
                    }
                }
            }
        }
    }
}
