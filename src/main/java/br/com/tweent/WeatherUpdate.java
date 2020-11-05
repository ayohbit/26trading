package br.com.tweent;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

public class WeatherUpdate implements Transaction<WeatherPersistence>, Serializable {

	private static final long serialVersionUID = 3L;

	private Weather weather;
	
	public WeatherUpdate(Weather weather) {
		this.weather = weather;
	}
	
	@Override
	public void executeOn(WeatherPersistence prevalentSystem, Date executionTime) {
		prevalentSystem.setWeather(weather);
	}

}
