package com.nivyox.gamemanager.games.events;

import com.nivyox.gamemanager.games.Game;
import com.nivyox.gamemanager.games.RemoveReason;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Niek on 30-6-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class GamePlayerRemoveEvent extends Event {
    private final Player player;
    private RemoveReason removereason;
    private final Game game;

    public GamePlayerRemoveEvent(Game game, Player player, RemoveReason removereason) {
        this.game = game;
        this.player = player;
        this.removereason = removereason;
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

    public RemoveReason getRemovereason() {
        return removereason;
    }
}
