package br.com.tweent;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CommonServlet extends HttpServlet {

	private static final long serialVersionUID = -2719305905906252729L;

	private static PlainFactory plainFactory;
	private static InfraeroFactory infraeroFactory;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletContextName = req.getSession().getServletContext().getServletContextName();
		req.setAttribute("contextName", servletContextName);
		super.service(req, resp);
	}
	
	protected synchronized PlainFactory getPlainFactory() throws FileNotFoundException {

		if (plainFactory == null) {
			plainFactory = new PlainFactory();

		}

		return plainFactory;
	}

	protected synchronized InfraeroFactory getInfraeroFactory() throws FileNotFoundException {

		if (infraeroFactory == null) {
			infraeroFactory = new InfraeroFactory();

		}

		return infraeroFactory;
	}

}
