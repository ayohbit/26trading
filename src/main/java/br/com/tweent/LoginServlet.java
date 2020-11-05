package br.com.tweent;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends CommonServlet {

	private static final long serialVersionUID = -5405643151353423613L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
		requestDispatcher.include(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		
		boolean valid = true;
		
		if (login == null || login.isEmpty()) {
			valid = false;
		}

		if (password == null || password.isEmpty()) {
			valid = false;
		}
		
		if (valid) {
			AuthenticationPainel user = new AuthenticationPainel(req.getSession());
			try {
				user.authenticate(login, password);
				
				String servletContextName = req.getSession().getServletContext().getServletContextName();
				
				resp.sendRedirect("/"+servletContextName+"/control");
			} catch (Exception e) {
				req.setAttribute("fail", e);
				
				doGet(req, resp);
			}
		}
	}
}
