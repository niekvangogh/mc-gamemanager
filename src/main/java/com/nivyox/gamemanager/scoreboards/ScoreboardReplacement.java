package com.nivyox.gamemanager.scoreboards;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ScoreboardReplacement {

    @Getter
    private String search;

    @Getter
    private Object replacement;
}
