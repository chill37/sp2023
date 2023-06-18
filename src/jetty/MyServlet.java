package jetty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		String path = req.getRequestURI();

		if (path.equals("/path1")) {
			// Logic for handling /path1
			writeToFile("Data for /path1");
			res.setStatus(HttpServletResponse.SC_OK);
			res.getWriter().println("Handling /path1");
		} else if (path.equals("/path2")) {
			// Logic for handling /path2
			writeToFile("Data for /path2");
			res.setStatus(HttpServletResponse.SC_OK);
			res.getWriter().println("Handling /path2");
		} else {
			// Path not supported
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			res.getWriter().println("Invalid path");
		}
	}

	private void writeToFile(String data) throws IOException {
		File file = new File("data.txt");
		FileWriter writer = new FileWriter(file, true); // Append mode

		writer.write(data);
		writer.write(System.lineSeparator()); // Add a new line
		writer.close();
	}
}
