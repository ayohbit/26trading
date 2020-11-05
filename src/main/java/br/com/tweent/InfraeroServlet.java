package br.com.tweent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/infraero")
public class InfraeroServlet extends CommonServlet {

	private static final long serialVersionUID = 6874392096101168634L;
	
	private static final int UPDATE_COUNT = 100;
	private int update = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Infraero infraero = getInfraeroFactory().get();
			
			if (infraero != null) {
				resp.getWriter().print(infraero.toString());
			}
		} catch (Exception e) {
			log(e.getMessage(), e);
		}
		
		if (update > UPDATE_COUNT) {
			update = 0;
		}
		
		update++;
		
	}
}
