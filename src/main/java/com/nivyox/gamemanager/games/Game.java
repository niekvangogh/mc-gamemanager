package com.nivyox.gamemanager.games;

import com.nivyox.gamemanager.GameManager;
import com.nivyox.gamemanager.games.arenas.Arena;
import com.nivyox.gamemanager.games.arenas.ArenaManager;
import com.nivyox.gamemanager.games.arenas.ArenaState;
import com.nivyox.gamemanager.games.arenas.exceptions.NoAvailableArenaException;
import com.nivyox.gamemanager.games.events.*;
import com.nivyox.gamemanager.scoreboards.ScoreboardsManager;
import com.nivyox.gamemanager.utils.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Niek on 30-6-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class Game {

    private final ScoreboardsManager scoreboardManager;
    private GameSettings gameSettings;
    private HashMap<UUID, GamePlayerDetails> players;
    private GameTimer gameTimer;
    private GameState gameState;
    private Arena arena;
    private BukkitTask timertask;
    private Scoreboard scoreboard;

    public Game(GameSettings gameSettings) throws NoAvailableArenaException {
        this.arena = ArenaManager.findAvailableArena();
        this.players = new HashMap<>();
        this.gameSettings = gameSettings;
        this.arena.setState(ArenaState.IN_USE);
        this.gameTimer = new GameTimer(this);
        this.gameState = GameState.WAITING;

        GameCreateEvent gameCreateEvent = new GameCreateEvent(this);
        Bukkit.getPluginManager().callEvent(gameCreateEvent);


        this.scoreboardManager = new ScoreboardsManager(this);
    }

    public void addPlayer(Player player) {
        this.players.put(player.getUniqueId(), new GamePlayerDetails(player));
        GamePlayerJoinEvent gamePlayerJoinEvent = new GamePlayerJoinEvent(this, player);
        Bukkit.getPluginManager().callEvent(gamePlayerJoinEvent);
    }

    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) getPlayers(Filter.NONE);
    }

    public List<Player> getPlayers(Filter filter) {
        ArrayList<Player> players = new ArrayList<>();
        switch (filter) {
            case NONE:
                this.players.keySet().forEach(player -> players.add(Bukkit.getPlayer(player)));
                break;
            case ONLINE:
                return this.getPlayers().stream().filter(OfflinePlayer::isOnline).collect(Collectors.toList());
        }
        return players;
    }

    public enum Filter {
        ONLINE, NONE
    }

    public void removePlayer(Player player, RemoveReason removereason) {
        GamePlayerRemoveEvent gamePlayerRemoveEvent = new GamePlayerRemoveEvent(this, player, removereason);
        Bukkit.getPluginManager().callEvent(gamePlayerRemoveEvent);
    }

    public void initStart() {
        if (timertask != null) {
            timertask.cancel();
        }
        this.timertask = Bukkit.getScheduler().runTaskTimer(GameManager.getPlugin(), getGameTimer(), 20, 20);
        getGameTimer().setTime(getGameSettings().pregameCountdown);
        GameStartEvent gameStartEvent = new GameStartEvent(this);
        Bukkit.getPluginManager().callEvent(gameStartEvent);
        setGameState(GameState.WAITING_COUNTDOWN);
    }

    public HashMap<UUID, GamePlayerDetails> getGamePlayers() {
        return players;
    }

    public ScoreboardsManager getScoreboardManager() {
        return scoreboardManager;
    }

    public ArrayList<UUID> getAlivePlayers() {
        return this.players.keySet().stream().filter(uuid -> players.get(uuid).isAlive).collect(Collectors.toCollection(ArrayList::new));
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState newState) {
        GameState oldState = gameState;
        this.gameState = newState;
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(this, oldState, newState));
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public void broadcast(String message) {
        GameManager.getInstance().getLogger().info(message);
        for (UUID uuid : players.keySet()) {
            if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
                Bukkit.getPlayer(uuid).sendMessage("[GAME] " + message);
            }
        }
    }

    public Arena getArena() {
        return arena;
    }

    public GamePlayerDetails getPlayer(Player player) {
        return players.get(player.getUniqueId());
    }

    public void endGame(UUID winningUUID, EndReason endReason) {
        Player winningPlayer = Bukkit.getPlayer(winningUUID);
        if (endReason == EndReason.PLAYER_WIN) {
            this.broadcast(winningPlayer.getDisplayName() + " has won the game!");
            gameTimer.endTime = gameTimer.getTime();
            setGameState(GameState.END);
        } else if (endReason == EndReason.CRASH) {
            finalizeGame();
        }
    }

    public void finalizeGame() {
        this.getPlayers(Filter.ONLINE).forEach(player -> player.teleport(Bukkit.getWorld((String) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_MAIN_LOBBY)).getSpawnLocation()));
        arena.setState(ArenaState.AVAILABE);
        timertask.cancel();
        GameManager.removeGame(this);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }
}