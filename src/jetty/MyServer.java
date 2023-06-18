package jetty;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MyServer {

	public static void main(String[] args) throws Exception {
		new MyServer().start();
	}

	public void start() throws Exception {
		Server server = new Server(8080);

		ServletContextHandler handler = new ServletContextHandler();
		handler.setContextPath("/");

		// Create an instance of your servlet
		MyServlet myServlet = new MyServlet();

		// Map the servlet to multiple URL patterns
		handler.addServlet(new ServletHolder(myServlet), "/path1");
		handler.addServlet(new ServletHolder(myServlet), "/path2");

		server.setHandler(handler);
		server.start();
		server.join();
	}
}
