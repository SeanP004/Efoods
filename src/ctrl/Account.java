package ctrl;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;
import model.OrderBean;


@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = (String)this.getServletContext().getAttribute("xmlPOFolderPath");
		String pathProcessed = (String)this.getServletContext().getAttribute("xmlPOProcessedFolderPath");
		String user = (String) request.getSession().getAttribute("user");
		
			
			try {
			  Engine engine = Engine.getInstance();
			  if (request.getParameter("view") != null) {
						//Server the PO view
					String view = request.getParameter("view") + ".xml";
					File file = new File(path + view);
					if (file.exists()) {
							OrderBean order = engine.convertFromXMLFileToObject(file, user);
							request.setAttribute("order", order);
					} else {
						 file = new File(pathProcessed + view);
						 if (file.exists()) {
							 OrderBean order = engine.convertFromXMLFileToObject(file, user);
							 request.setAttribute("order", order);
						 } else {
							 request.setAttribute("error", "File not found");
						 }
						 
					}
					
				}
				else {
					request.setAttribute("fileNames", engine.getXMLLinks(user, path));
					request.setAttribute("processedfileNames", engine.getXMLLinks(user, pathProcessed));
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
			this.getServletContext().getRequestDispatcher("/Account.jspx").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
