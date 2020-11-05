package br.com.tweent;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.prevayler.Prevayler;

@WebServlet("/control")
@MultipartConfig
public class ControlServlet extends CommonServlet {

	private static final long serialVersionUID = -5269253648262015215L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/control.jsp");
		requestDispatcher.include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("csv");

		if (part != null) {
			InputStream inputStream = part.getInputStream();

			StringWriter writer = new StringWriter();
			IOUtils.copy(inputStream, writer);

			if (writer.toString().equals("")) {
				req.setAttribute("fail", true);
			} else {
				Plain plain = new Plain(writer.toString());

				try {
					getPlainFactory().set(plain);
				} catch (Exception e) {
					req.setAttribute("fail", true);
					e.printStackTrace();
				}

				req.setAttribute("success", true);
			}
		}

		Part part2 = req.getPart("ranking");

		if (part2 != null) {

			InputStream inputStream2 = part2.getInputStream();

			byte[] byteArray = IOUtils.toByteArray(inputStream2);
			String base64 = Base64.getEncoder().encodeToString(byteArray);
			
			Infraero infraero = new Infraero(base64);
			
			try {
				getInfraeroFactory().set(infraero);
			} catch (Exception e) {
				req.setAttribute("fail", true);
				e.printStackTrace();
			}
			
			req.setAttribute("success", true);
		}
		
		String appid = req.getParameter("APPID");

		if (appid != null && !appid.isEmpty()) {
			Weather weather = new Weather(appid);
			try {
				Prevayler<WeatherPersistence> open = PersistenceFactory.open(new WeatherPersistence());
				open.execute(new WeatherUpdate(weather));
//				open.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		doGet(req, resp);
	}

}
