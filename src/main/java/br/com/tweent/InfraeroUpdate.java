package br.com.tweent;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

public class InfraeroUpdate implements Transaction<InfraeroPersistence>, Serializable {

	private static final long serialVersionUID = 3L;

	private Infraero infraero;
	
	public InfraeroUpdate(Infraero infraero) {
		this.infraero = infraero;
	}
	
	@Override
	public void executeOn(InfraeroPersistence prevalentSystem, Date executionTime) {
		prevalentSystem.setInfraero(infraero);
	}

}
