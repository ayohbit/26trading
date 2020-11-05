package br.com.tweent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/plain")
public class PlainServlet extends CommonServlet {

	private static final long serialVersionUID = 254030446606617537L;
	private static final int NUMBER_ROWS = 10;
	private static final int UPDATE_COUNT = 100;
	private int begin = 0;
	private int update = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Plain plain;
		try {
			plain = getPlainFactory().get();
			
			if (plain == null) {
				throw new Exception("Deve importar o arquivo primeiro");
			}
			
			List<String> headers = new ArrayList<>();
			headers.add("PO");
			headers.add("Cliente");
			headers.add("ETD");
			headers.add("ETA");
			headers.add("Dias");
			
			plain.setDisplayLines(NUMBER_ROWS);
			plain.setBegin(begin);
			plain.setSelectColunms(headers);
			begin = plain.getBegin();
			
			req.setAttribute("headers", plain.getHearders());
			req.setAttribute("list", plain.getList());
			
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/plain.jsp");
			requestDispatcher.include(req, resp);
		} catch (Exception e) {
			log(e.getMessage());
		}
		
		if (update > UPDATE_COUNT) {
//			begin += NUMBER_ROWS;
			update = 0;
		}
		
		update++;
	}
	
}
