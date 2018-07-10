package com.sample.url.shortner.service;

import java.util.Date;

public class UrlShortner {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int BASE = ALPHABET.length();

    /**
     * This method provide short url based on the current time and passed in id.
     * 
     * @param id Unique id of the persistence store
     * @return String shortened url
     */
    public static String createUniqueId(long id) {
        long time = new Date().getTime();

        long saltedNumber = time + 10 * id;

        StringBuilder sb = new StringBuilder();
        while (saltedNumber > 0) {
            sb.append(ALPHABET.charAt((int) (saltedNumber % BASE)));
            saltedNumber /= BASE;
        }
        return sb.reverse().toString();
    }
}
