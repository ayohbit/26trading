package br.com.tweent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.prevayler.Prevayler;

@WebServlet("/weather")
public class WeatherServlet extends CommonServlet {

	private static final long serialVersionUID = 1773068791873922057L;
	
	private String content = "";
	
	private int update;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (update == 0) {
			try {
				Prevayler<WeatherPersistence> open = PersistenceFactory.open(new WeatherPersistence());
				
				WeatherPersistence weatherPersistence = open.prevalentSystem();
				
				if (weatherPersistence.getWeather() == null) {
					throw new Exception("Configure o APPID no control para visualizar a previsao do tempo");
				}
				
				URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Recife,brazil&units=metric&lang=pt&APPID="+weatherPersistence.getWeather().getAppid());
				
//				open.close();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
				
				StringBuilder builder = new StringBuilder();
				
				String line;
				while((line = reader.readLine()) != null) {
					builder.append(line);
				}
				
				content = builder.toString();
			} catch (Exception e) {
				log(e.getMessage());
			}
		}
		
		update++;
		
		PrintWriter writer = resp.getWriter();
		writer.print(content);
		
		int limit = 0;
		try {
			String limitString = (String)req.getParameter("limit");
			limit = Integer.parseInt(limitString);
		} catch(Exception e) {
			limit = 100;
		}
		
		if (update == limit) {
			update = 0;
		}
	}
}
