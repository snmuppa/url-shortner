package com.sample.url.shortner.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

@Repository
public class UrlRepository {
    private final Jedis jedis;

    private final String idKey;

    private final String urlKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlRepository.class);

    public UrlRepository() {
        jedis = new Jedis();
        idKey = "id";
        urlKey = "url:";
    }

    public UrlRepository(Jedis jedis, String idKey, String urlKey) {
        this.jedis = jedis;
        this.idKey = idKey;
        this.urlKey = urlKey;
    }

    public Long incrementId() {
        Long id = jedis.incr(idKey);
        LOGGER.info("Incrementing ID: {}", id - 1);
        return id - 1;
    }

    public void saveUrl(String key, String longUrl) {
        LOGGER.info("Saving: {} at {}", longUrl, key);
        jedis.hset(urlKey, key, longUrl);
    }

    public String getUrl(String key) throws Exception {
        LOGGER.info("Retrieving at {}", key);
        String url = jedis.hget(urlKey, key);
        if (url == null) {
            throw new Exception("URL at key: " + key + " does not exist");
        }
        return url;
    }
}
