package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Engine;

@WebServlet({ "/Browse"})
public class Browse extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public Browse() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		session.setAttribute("back", request.getRequestURL().toString());
		try
		{
			Engine engine = Engine.getInstance();
			request.setAttribute("catalogs", engine.getCatalogs());
			if(request.getParameter("cat") != null)
			{
				session.setAttribute("previousCat", request.getParameter("cat"));
				request.setAttribute("items",engine.getItems(request.getParameter("cat").toString()));
			} 
			else if (session.getAttribute("previousCat") != null) {
				request.setAttribute("items",engine.getItems(session.getAttribute("previousCat").toString()));
			}
			else if(request.getParameter("select_item").equals("search"))
			{
				System.out.println("request to search");
				request.setAttribute("items",engine.searchItem(request.getParameter("number")));
			}
		}
		catch (Exception e)
		{
			request.setAttribute("error", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/Browse.jspx").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}

























