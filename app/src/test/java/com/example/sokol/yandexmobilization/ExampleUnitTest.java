package com.example.sokol.yandexmobilization;

import android.net.Uri;

import org.junit.Test;

import java.net.URI;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public static final String GET_LANGS_URL = "https://translate.yandex.net/com.example.sokol.yandexmobilization.api/v1.5/tr.json/getLangs";
    public static final String KEY_PARAM = "key";
    public static final String API_KEY = "trnsl.1.1.20170315T185651Z.010400ec63887d48.13cf882cbb4e9428daa4cbf12666f3e468abf45c";

    @Test
    public void translate(){
        URI uri = URI.create(GET_LANGS_URL);
        System.out.println(uri);
        Uri androidUri = Uri.parse(uri.toString());
        System.out.println(androidUri);
    }

}