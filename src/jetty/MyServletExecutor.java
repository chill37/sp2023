package jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServletExecutor extends HttpServlet {
    private ExecutorService executorService;

    public MyServletExecutor() {
        // Create a fixed-size thread pool with 10 threads
        executorService = Executors.newFixedThreadPool(10);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getRequestURI();

        if (path.equals("/path1")) {
            // Retrieve the parameter value from the query string
            String paramValue = request.getParameter("param");

            // Execute the task for /path1 in a separate thread and pass the parameter
            executorService.execute(() -> handlePath1Request(response, paramValue));
        } else if (path.equals("/path2")) {
            // Retrieve the parameter value from the query string
            String paramValue = request.getParameter("param");

            // Execute the task for /path2 in a separate thread and pass the parameter
            executorService.execute(() -> handlePath2Request(response, paramValue));
        } else {
            // Path not supported
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("Invalid path");
        }
    }

    private void handlePath1Request(HttpServletResponse response, String paramValue) {
        // Logic for handling /path1 request with the parameter
        try {
            Thread.sleep(2000); // Simulate some processing time
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Handling GET request to /path1 with parameter: " + paramValue);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private void handlePath2Request(HttpServletResponse response, String paramValue) {
        // Logic for handling /path2 request with the parameter
        try {
            Thread.sleep(3000); // Simulate some processing time
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Handling GET request to /path2 with parameter: " + paramValue);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);

        // Register the servlet
        context.addServlet(new ServletHolder(new MyServlet()), "/*");

        server.start();
        server.join();
    }
}
