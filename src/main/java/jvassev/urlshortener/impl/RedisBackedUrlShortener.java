package jvassev.urlshortener.impl;

import jvassev.urlshortener.UrlShortener;
import redis.clients.jedis.Jedis;

public class RedisBackedUrlShortener implements UrlShortener {

	private NumberShortener shortener;
	private RedisBackedCounter counter;
	private Jedis jedis;
	private String namespace;

	public RedisBackedUrlShortener(Jedis jedis, String namespace) {
		this.jedis = jedis;
		this.counter = new RedisBackedCounter(jedis, namespace + "-counter");
		this.shortener = new NumberShortener();
		this.namespace = namespace;
	}

	public String shorten(String string) {
		long next = counter.next();
		String shortened = shortener.shorten(next);
		put(shortened, string);
		return shortened;
	}

	private void put(String key, String value) {
		jedis.hset(namespace, key, value);
	}

	public String expand(String string) {
		return jedis.hget(namespace, string);
	}
}
