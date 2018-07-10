package com.samples.url.shorner.entity;

/**
 * This class used to set and get the url and count values.
 */
public class Url {
    private String shortUrl;

    private String longUrl;

    public Url() {}

    /**
     * Constructor used to initialize the value.
     * 
     * @param shortUrl
     * @param longUrl
     */
    public Url(String shortUrl, String longUrl) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    /**
     * Getter method used to get shorturl.
     * 
     * @return String
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * Getter method used to get long url.
     * 
     * @return String
     */
    public String getLongUrl() {
        return longUrl;
    }

    @Override
    public String toString() {
        return String.format("Url[shortUrl='%s', longUrl='%s']", shortUrl, longUrl);
    }
}
