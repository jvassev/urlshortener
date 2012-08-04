package jvassev.urlshortener;

import jvassev.urlshortener.impl.RedisBackedUrlShortener;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class IntegrationTest {
	@Test
	public void it() throws Exception {
		Jedis jedis = new Jedis("localhost");

		RedisBackedUrlShortener encoder = new RedisBackedUrlShortener(jedis, "urls");
		String shortened = encoder.shorten("http://google.com");
		System.out.println(encoder.expand(shortened));
	}
}
