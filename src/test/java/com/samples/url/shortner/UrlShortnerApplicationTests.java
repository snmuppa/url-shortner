package com.samples.url.shortner;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sample.url.shortner.repository.UrlRepository;

import ai.grakn.redismock.RedisServer;
import redis.clients.jedis.Jedis;

public class UrlShortnerApplicationTests {
    private static RedisServer server;

    @BeforeClass
    public static void setupServer() throws IOException {
        server = RedisServer.newRedisServer(6789);
        server.start();
    }

    @AfterClass
    public static void shutdownServer() throws IOException {
        server.stop();
    }

    @Test
    public void test_incrementID_StartsAt0AndIncrements() {
        UrlRepository urlRepository = new UrlRepository(new Jedis(server.getHost(), server.getBindPort()), "id", "url:");
        for (long expectedId = 0L; expectedId < 50L; ++expectedId) {
            long actualId = urlRepository.incrementId();
            assertEquals(expectedId, actualId);
        }
    }
}
