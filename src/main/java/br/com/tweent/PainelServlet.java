package br.com.tweent;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/index.html", "/index" })
public class PainelServlet extends CommonServlet {

	private static final long serialVersionUID = -2981350929138832350L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/painel.jsp");
		requestDispatcher.include(req, resp);
	}
}
