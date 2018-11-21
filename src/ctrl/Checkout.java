package ctrl;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;
import model.OrderBean;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Checkout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OrderBean order = (OrderBean) request.getSession().getAttribute("order");
		//User is guranteed to have a value because a filter takes cares of that
		String user = (String) request.getSession().getAttribute("user");
		String path = (String)this.getServletContext().getAttribute("xmlPOFolderPath");
		String pathProcessed = (String)this.getServletContext().getAttribute("xmlPOProcessedFolderPath");
		
		//checkout only if the user is logged in and the cart is not empty and getParameter 
		if (!order.isEmpty() && request.getParameter("force") != null && user != null) {
			if (this.getServletContext().getAttribute("totalPO") == null) 
				this.getServletContext().setAttribute("totalPO", 0);
			
			int count = (int) this.getServletContext().getAttribute("totalPO");
			
			order.setSubmitted(new Date());
			order.setAccount(user);
			order.setOrderNumber(count);
			
			count++;
			this.getServletContext().setAttribute("totalPO", count);
			
			request.setAttribute("order", order);
			try {
				Engine engine = Engine.getInstance();
				engine.jaxbObjectToXML(order, "PO_" + count, path); 
				order.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
			
	    try {
				  Engine engine = Engine.getInstance();
				  request.setAttribute("fileNames", engine.getXMLLinks(user, path));
				  request.setAttribute("processedfileNames", engine.getXMLLinks(user, pathProcessed));
				 
		} catch (Exception e) {
			   e.printStackTrace();
		}			
		
		
		this.getServletContext().getRequestDispatcher("/Checkout.jspx").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
