

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Map<String, String> map = new HashMap<String, String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print(
				"<form method=\"post\">"
				+ "Key: <br>"
				+ "<input type=\"text\" name=\"key\"><br>"
				+ "Value: <br> "
				+ "<input type=\"text\" name=\"value\"><br><br>"
				+ "<input type=\"submit\" value=\"Submit\">"
				+ "</form>" 
		);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		map.put(request.getParameter("key"), request.getParameter("value"));
		
		for(Map.Entry<String, String> map : map.entrySet()) {
			out.print(map.getKey() + " - " + map.getValue() + "<br>");
		}
		
	}

}
