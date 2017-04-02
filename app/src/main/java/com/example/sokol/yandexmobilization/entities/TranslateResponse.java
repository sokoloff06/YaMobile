package com.example.sokol.yandexmobilization.entities;

/**
 * Created by sokol on 21.03.2017.
 */

public class TranslateResponse {

    private Integer code;
    private String lang;
    private String text;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setText(String[] text) {
        this.text = text[0];
    }

    public String getText() {
        return text;
    }
}
