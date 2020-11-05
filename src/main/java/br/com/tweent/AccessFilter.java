package br.com.tweent;

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

@WebFilter(urlPatterns={"/control", "*.jsp"})
public class AccessFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		
		if (httpServletRequest.getSession().getAttribute("login") == null) {
			String servletContextName = httpServletRequest.getSession().getServletContext().getServletContextName();
			
			httpServletResponse.sendRedirect("/"+servletContextName+"/login");
			
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
