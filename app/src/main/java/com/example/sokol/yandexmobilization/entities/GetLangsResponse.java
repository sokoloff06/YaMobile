package com.example.sokol.yandexmobilization.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sokol on 22.03.2017.
 */

public class GetLangsResponse {

    private String[] dirs;
    private Map<String, String> langs;

    public String[] getDirs() {
        return dirs;
    }

    public void setDirs(String[] dirs) {
        this.dirs = dirs;
    }

    public Map<String, String> getLangs() {
        return langs;
    }

    public void setLangs(Map<String, String> langs) {
        this.langs = langs;
    }
}
