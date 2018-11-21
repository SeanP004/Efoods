package adhoc;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import model.Engine;
import model.ItemBean;

/**
 * Servlet Filter implementation class Advertise
 */
//@WebFilter(dispatcherTypes = {
//				DispatcherType.REQUEST, 
//				DispatcherType.FORWARD
//		}
//		, urlPatterns = { "/Advertise","/Add.jspx" }, servletNames = { "Add" })
public class Advertise implements Filter 
{
	String number_hook_item ="1409S413";
	String number_advertise_item = "2002H712";
	public Advertise() 
    {
    }

	public void destroy() 
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
	
	
		
		
		//check if it is item  1409S413
		HttpServletRequest req = (HttpServletRequest) request;
		String select_item = req.getParameter("select_item");
		if(select_item.equals("Add To Cart") && req.getParameter("number").equals(number_hook_item))
		{
			try
			{
				Engine engine = Engine.getInstance();
				request.setAttribute("advertise", engine.getItem(number_advertise_item));
				request.setAttribute("toadvertise", "true");
			}
			catch (Exception e)
			{
				System.out.println("Failed to hook advertisement");
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
