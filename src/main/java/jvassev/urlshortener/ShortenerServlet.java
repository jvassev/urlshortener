package jvassev.urlshortener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import redis.clients.jedis.Jedis;

import jvassev.urlshortener.impl.RedisBackedUrlShortener;

public class ShortenerServlet extends HttpServlet {

	private static final long serialVersionUID = 2040765997590119042L;
	
	private UrlShortener shortener;

	@Override
	public void init() throws ServletException {
		String redisUrl = System.getProperty("redis", "localhost");
		int port = 6379;

		int split = redisUrl.indexOf(':');
		if (split > 0) {
			redisUrl = redisUrl.substring(0, split);
			port = Integer.parseInt(redisUrl.substring(split + 1));
		}

		String namespace = System.getProperty("namespace", "urls");
		Jedis jedis = new Jedis(redisUrl, port);
		shortener = new RedisBackedUrlShortener(jedis, namespace);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = req.getRequestURI();
		String shortened = url.substring(req.getContextPath().length() + 1);

		if (shortened == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			String full = shortener.expand(shortened);
			if (full != null) {
				resp.setHeader("Location", full);
				resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = req.getRequestURI();
		String full = url.substring(req.getContextPath().length() + 1);
		if (full.equals("") || full == null) {
			full = IOUtils.toString(req.getInputStream());
		}

		String shortened = shortener.shorten(full);
		resp.getWriter().print(shortened);
	}
}
