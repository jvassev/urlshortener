package jvassev.urlshortener;

import static org.junit.Assert.*;
import jvassev.urlshortener.impl.NumberShortener;

import org.junit.Test;

public class NumberShortenerTest {
	@Test
	public void ad() throws Exception {
		NumberShortener ns = new NumberShortener();
		assertEquals("v", ns.shorten(0));
		assertEquals("a", ns.shorten(1));
		assertEquals("m", ns.shorten(2));
		System.out.println(ns.shorten(Long.MAX_VALUE));
	}
}
