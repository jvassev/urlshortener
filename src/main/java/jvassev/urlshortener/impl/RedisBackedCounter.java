package jvassev.urlshortener.impl;

import java.io.UnsupportedEncodingException;

import redis.clients.jedis.Jedis;

public class RedisBackedCounter {
	private Jedis jedis;
	private byte[] counterName;

	public RedisBackedCounter(Jedis jedis, String counterName) {
		this.jedis = jedis;
		try {
			this.counterName = counterName.getBytes("ASCII");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public long next() {
		return jedis.incr(counterName);
	}
}
