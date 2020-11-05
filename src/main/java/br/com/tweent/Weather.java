package br.com.tweent;

import java.io.Serializable;

public class Weather implements Serializable {

	private static final long serialVersionUID = 1L;
	private String appid;
	
	public Weather(String appid) {
		this.appid = appid;
	}
	
	public String getAppid() {
		return appid;
	}
}
