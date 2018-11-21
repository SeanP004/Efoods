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

import ctrl.CtrlUtil;

/**
 * Servlet Filter implementation class Logging
 */
@WebFilter({ "/Logging", "/*" })
public class Logging implements Filter 
{

    public Logging() 
    {
    }

	public void destroy() 
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		CtrlUtil.log_request(System.out, (HttpServletRequest) request, "Incoming");
		chain.doFilter(request, response);
		CtrlUtil.log_request(System.out, (HttpServletRequest) request, "Outgoing");
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{
	}
}
