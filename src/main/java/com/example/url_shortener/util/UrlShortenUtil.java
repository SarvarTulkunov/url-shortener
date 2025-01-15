package com.example.url_shortener.util;

import java.util.Random;

public class UrlShortenUtil {

    private static final String BASE62_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int KEY_LENGTH = 6;

    public static String encodeToBase62(long num) {
        StringBuilder shortUrl = new StringBuilder();
        while (num > 0) {
            shortUrl.append(BASE62_CHARACTERS.charAt((int) (num % 62)));
            num /= 62;
        }

        Random random = new Random();
        while (shortUrl.length() < KEY_LENGTH) {
            shortUrl.append(BASE62_CHARACTERS.charAt(random.nextInt(BASE62_CHARACTERS.length())));
        }

        return shortUrl.reverse().toString();
    }

}
