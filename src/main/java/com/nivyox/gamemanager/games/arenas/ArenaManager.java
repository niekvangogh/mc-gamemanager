package com.nivyox.gamemanager.games.arenas;

import com.nivyox.gamemanager.games.GameSpecifications;
import com.nivyox.gamemanager.GameManager;
import com.nivyox.gamemanager.games.arenas.exceptions.NoAvailableArenaException;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Niek on 1-7-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class ArenaManager {

    private static ArrayList<Arena> arenas = new ArrayList<>();

    public static ArrayList<Arena> getArenas() {
        return arenas;
    }

    public static void addArena(Arena... lArenas) {
        arenas.addAll(Arrays.asList(lArenas));
    }

    public static void removeArena(Arena lArenas) {
        arenas.removeAll(Arrays.asList(lArenas));
    }

    public static Arena findAvailableArena() throws NoAvailableArenaException {
        Arena tArena = arenas.stream().filter(arena -> arena.getState() == ArenaState.AVAILABE).findFirst().orElse(null);
        if (tArena == null) {
            throw new NoAvailableArenaException();
        }
        return tArena;
    }

    public static void loadArenas() {
        String arenaNaming = GameSpecifications.getGameArenaNaming();
        int amount = GameSpecifications.getRequestedArenaSize();
        for (int i = 0; i < amount; i++) {
            Arena arena = new Arena(createArena(arenaNaming + "_" + i));
            GameManager.getInstance().getLogger().info("Loaded arena: " + arena.getWorld().getName());
            arenas.add(arena);
        }
    }

    public static World createArena(String name) {
        WorldCreator worldCreator = new WorldCreator(name);
        return Bukkit.createWorld(worldCreator);
    }
}