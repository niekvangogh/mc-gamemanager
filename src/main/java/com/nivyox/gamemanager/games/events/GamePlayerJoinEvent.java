package com.nivyox.gamemanager.games.events;

import com.nivyox.gamemanager.games.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class GamePlayerJoinEvent extends Event {
    private final Game game;
    private final Player player;

    public GamePlayerJoinEvent(Game game, Player player) {
        this.player = player;
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
