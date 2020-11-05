package br.com.tweent;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cotacao")
public class CotacaoServlet extends CommonServlet {

	private static final long serialVersionUID = 5445781608423516663L;
	private CotacaoFactory factory = new CotacaoFactory();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String codigo = req.getParameter("codigo");

		if (codigo == null || codigo.isEmpty()) {
			codigo = "61";
		}

		try {
			req.setAttribute("cotacao", factory.get(codigo));
			
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/cotacao.jsp");
			requestDispatcher.include(req, resp);
		} catch (Exception e) {
			log(e.getMessage(), e);
		}
	}
}
