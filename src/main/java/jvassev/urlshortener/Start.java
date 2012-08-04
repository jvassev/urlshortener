package jvassev.urlshortener;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Start {
	public static void main(String[] args) throws Exception {
		System.out.println("Available customizations:");
		System.out.println("\t-Dport (8080) port to run webserver");
		System.out.println("\t-Dredis (localhost:6379) location of redis server");
		System.out.println("\t-Dnamespace (urls) root path in redis where to store program data");
		System.out.println("\t-Dhost (http://localhost:8080) root path in redis where to store program data");
		Server server = new Server(Integer.valueOf(System.getProperty("port", "8080")));
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

		context.addServlet(new ServletHolder(new ShortenerServlet()), "/*");

		context.setClassLoader(Thread.currentThread().getContextClassLoader());
		context.setContextPath("/");
		context.setResourceBase(".");
		server.setHandler(context);

		server.start();
	}
}
