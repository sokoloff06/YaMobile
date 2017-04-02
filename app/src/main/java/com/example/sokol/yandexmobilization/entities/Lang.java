package com.example.sokol.yandexmobilization.entities;

/**
 * Created by sokol on 22.03.2017.
 */

public class Lang {

    private String code;
    private String name;

    public Lang(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
