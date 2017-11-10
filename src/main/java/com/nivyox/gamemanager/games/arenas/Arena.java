package com.nivyox.gamemanager.games.arenas;

import com.nivyox.gamemanager.utils.ConfigHandler;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

public class Arena {

    private final World world;
    private List<Location> pregameSpawnLocations;
    private List<Location> chestlocations;

    public Arena(World world) {
        this.world = world;
        state = ArenaState.AVAILABE;
        this.pregameSpawnLocations = ConfigHandler.getLocation(ConfigHandler.ConfigPaths.GAME_ARENA_SPAWNLOCATION, this);
    }

    private ArenaState state;

    public ArenaState getState() {
        return state;
    }

    public void setState(ArenaState state) {
        this.state = state;
    }

    public World getWorld() {
        return world;
    }

    public List<Location> getPregameSpawnLocations() {
        return pregameSpawnLocations;
    }

}