package br.com.tweent;

import java.io.Serializable;

public class InfraeroPersistence implements Serializable {

	private static final long serialVersionUID = 2L;

	private Infraero infraero;

	public void setInfraero(Infraero infraero) {
		this.infraero = infraero;
	}
	
	public Infraero getInfraero() {
		return infraero;
	}
}
