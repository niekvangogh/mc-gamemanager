package com.nivyox.gamemanager.games.listeners;

import com.nivyox.gamemanager.GameManager;
import com.nivyox.gamemanager.games.*;
import com.nivyox.gamemanager.games.events.GamePlayerDeathEvent;
import com.nivyox.gamemanager.games.events.GamePlayerJoinEvent;
import com.nivyox.gamemanager.games.events.GamePlayerReconnectEvent;
import com.nivyox.gamemanager.games.events.GamePlayerRemoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class GamePlayerListener implements Listener {

    @EventHandler
    public void gamePlayerJoinEvent(GamePlayerJoinEvent event) {
        Player player = event.getPlayer();
        Game game = event.getGame();
        player.teleport(game.getArena().getWorld().getSpawnLocation());
        if (game.getGameState() == GameState.WAITING) {
            game.getPlayer(player).isAlive = true;
        }
        game.getScoreboardManager().giveScoreboard(player);
        game.broadcast(player.getDisplayName() + " has joined! [" + game.getAlivePlayers().size() + "/" + game.getGameSettings().maxPlayers + "]");
        if (game.getAlivePlayers().size() >= game.getGameSettings().minPlayers) {
            game.initStart();
        }
    }

    @EventHandler
    public void gamePlayerDeathEvent(GamePlayerDeathEvent event) {
        Game game = event.getGame();
        Player player = event.getPlayer();
        game.getPlayer(player).isAlive = false;
        game.getPlayer(player).isSpectator = true;
    }

    @EventHandler
    public void gamePlayerReconnectedEvent(GamePlayerReconnectEvent event) {
        Game game = event.getGame();
        Player player = event.getPlayer();

        if (game.getGameState() != GameState.WAITING) {
            if (game.getPlayer(player).isAlive) {
                game.getPlayer(player).isAlive = false;
            }
            game.getPlayer(player).isSpectator = true;
        } else {
            if (game.getAlivePlayers().size() >= game.getGameSettings().maxPlayers) {
                game.removePlayer(player, RemoveReason.NO_SPACE_AFTER_RECONNECT);
            }
            game.getPlayer(player).isAlive = true;
            game.getPlayer(player).isSpectator = false;
            Bukkit.getPluginManager().callEvent(new GamePlayerJoinEvent(game, player));
        }
    }

    @EventHandler
    public void gamePlayerRemoveEvent(GamePlayerRemoveEvent event) {
        Game game = event.getGame();
        Player player = event.getPlayer();
        RemoveReason removeReason = event.getRemovereason();
        game.getPlayer(player).isSpectator = true;
        game.getPlayer(player).isAlive = false;

        if (removeReason == RemoveReason.LEFT || removeReason == RemoveReason.NO_SPACE_AFTER_RECONNECT) {
            GameManager.getInstance().getLogger().info(player.getName() + " has been removed from the game since it was full");
            game.getGamePlayers().remove(player.getUniqueId());
        }
        if (game.getGameState() == GameState.PREGAME_COUNTDOWN) {
            if (game.getGameSettings().minPlayers >= game.getPlayers().size()) {
                game.setGameState(GameState.WAITING);
                game.broadcast("The countdown has been stopped because there are not enough players in the game!");
            }
            game.broadcast(player.getDisplayName() + " has quit!");
        } else {
            Bukkit.getPluginManager().callEvent(new GamePlayerDeathEvent(game, player, null));
        }
    }
}