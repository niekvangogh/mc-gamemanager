package com.nivyox.gamemanager;

import com.nivyox.gamemanager.games.Game;
import com.nivyox.gamemanager.games.GameSettings;
import com.nivyox.gamemanager.games.GameSpecifications;
import com.nivyox.gamemanager.games.GameState;
import com.nivyox.gamemanager.games.arenas.ArenaManager;
import com.nivyox.gamemanager.games.arenas.exceptions.NoAvailableArenaException;
import com.nivyox.gamemanager.games.exceptions.NoAvailableGamesException;
import com.nivyox.gamemanager.games.listeners.GameListener;
import com.nivyox.gamemanager.games.listeners.GamePlayerListener;
import com.nivyox.gamemanager.listeners.PlayerListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Niek on 30-6-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class GameManager {

    private static ArrayList<Game> games = new ArrayList<>();

    private static GameManager instance;
    private Logger logger;

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static void addGame(Game game) {
        games.add(game);
        System.out.println(games.toString());
    }

    public static void removeGame(Game game) {
        games.remove(game);
    }

    public static Game getGame(Player player) {
        return getGame(player.getUniqueId());
    }

    public static Game getGame(UUID uuid) {
        for (Game game : games) {
            for (UUID storedUuid : game.getGamePlayers().keySet()) {
                if (storedUuid.toString().equals(uuid.toString())) {
                    return game;
                }
            }
        }
        return null;
    }

    public static Game findAvailableGame() throws NoAvailableGamesException {
        for (Game game : games) {
            if (game.getGameState() == GameState.WAITING || game.getGameState() == GameState.PREGAME_COUNTDOWN) {
                if (game.getPlayers().size() < game.getGameSettings().maxPlayers) {
                    return game;
                }
            }
        }
        try {
            return createNewGame();
        } catch (NoAvailableArenaException e) {
            throw new NoAvailableGamesException();
        }
    }

    public static Game createNewGame(GameSettings gameSettings) throws NoAvailableArenaException {
        try {
            return customGameClass.getConstructor(GameSettings.class).newInstance(gameSettings);
        } catch (Exception e) {
            return new Game(gameSettings);
        }
    }

    public static Game createNewGame() throws NoAvailableArenaException {
        return createNewGame(new GameSettings());
    }

    public static void initGameManager(JavaPlugin plugin) {
        new GameManager(plugin);
    }

    private void registerListeners(Listener... gameListener) {
        for (Listener listener : gameListener) {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        }
    }

    public static GameManager getInstance() {
        return instance;
    }

    public GameManager(JavaPlugin plugin) {
        setPlugin(plugin);
        instance = this;
        this.logger = Logger.getLogger("GameManager");

        getLogger().info(
                "Gamename: " + GameSpecifications.getGamename()
                        + "\tGame version: " + GameSpecifications.getGameVersion()
                        + "\tGame description: " + GameSpecifications.getGameDescription()
        );

        registerListeners(new GamePlayerListener(), new GameListener(), new PlayerListener());

        ArenaManager.loadArenas();
    }

    public Logger getLogger() {
        return logger;
    }

    @Getter()
    @Setter(AccessLevel.MODULE)
    private static JavaPlugin plugin;

    @Setter
    private static Class<? extends Game> customGameClass;
}