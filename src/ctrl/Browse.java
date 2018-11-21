package ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;
import model.ItemBean;

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
		try
		{
			Engine engine = Engine.getInstance();
			request.setAttribute("catalogs", engine.getCatalogs());
			if(request.getParameter("cat")!=null)
			{
				request.setAttribute("items",engine.getItems(request.getParameter("cat")));
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

























