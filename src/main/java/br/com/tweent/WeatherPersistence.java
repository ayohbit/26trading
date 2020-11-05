package br.com.tweent;

import java.io.Serializable;

public class WeatherPersistence implements Serializable {

	private static final long serialVersionUID = 2L;

	private Weather weather;
	
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	
	public Weather getWeather() {
		return weather;
	}
}
