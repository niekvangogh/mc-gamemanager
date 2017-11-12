package com.nivyox.gamemanager.games;

import java.util.HashMap;

public class GamePlayerDetails {
    public boolean isAlive;
    public boolean isSpectator;


    private HashMap<String, Object> custom = new HashMap<>();


    public GamePlayerDetails() {
    }

    public void set(String key, Object value) {
        custom.put(key, value);
    }

    public Object get(String key) {
        return custom.get(key);
    }

    public void addOneTo(String key) {
        int value = (int) custom.get(key);
        value++;
        custom.replace(key, value);
    }

    public void removeOneFrom(String key) {
        int value = (int) custom.get(key);
        value--;
        custom.replace(key, value);
    }
}
