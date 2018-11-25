package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/Login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		if (request.getParameter("user")!=null)
		{
			String user= request.getParameter("user");
			String name = request.getParameter("name");
			String back = (String) request.getSession().getAttribute("back");
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("name",name);
			request.setAttribute("user", user);
			request.setAttribute("name", name);
			//this.getServletContext().getRequestDispatcher("/Login.jspx").forward(request, response);
			response.sendRedirect(back);
		}
		else if(request.getParameter("login")!=null)
		{
			String url = "https://www.eecs.yorku.ca/~roumani/servers/auth/oauth.cgi"+
					"?back="+(request.getRequestURL());
			response.sendRedirect(url);
		}
		else
		{
			request.setAttribute("error", "Wrong Account Number and/or PIN.\\nPlease check your Account and PIN number you have entered");
			this.getServletContext().getRequestDispatcher("/Login.jspx").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
