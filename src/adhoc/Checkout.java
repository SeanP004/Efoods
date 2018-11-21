package adhoc;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Checkout
 */
@WebFilter("/Checkout")
public class Checkout implements Filter {

    public Checkout() 
    {
    }

	public void destroy() 
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getSession().getAttribute("user")!=null)
		{
			chain.doFilter(request, response);			
		}
		else
		{
			((HttpServletResponse)response).sendRedirect("Login?login=true");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{
	}

}
