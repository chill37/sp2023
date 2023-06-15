package jetty;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		Enumeration e = req.getParameterNames();
		while ( e.hasMoreElements() ){
			String name = (String) e.nextElement();
			String[] values = req.getParameterValues(name);		
			for (String value : values) {
				System.out.println("name=" + name + ",value=" + value);
			}   
		}

		res.setStatus(200);
		res.getWriter().write("Hello! ");
	}
}
