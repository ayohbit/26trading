package br.com.tweent;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

public class PlainUpdate implements Transaction<PlainPersistence>, Serializable {

	private static final long serialVersionUID = 3L;

	private Plain plain;
	
	public PlainUpdate(Plain plain) {
		this.plain = plain;
	}
	
	@Override
	public void executeOn(PlainPersistence prevalentSystem, Date executionTime) {
		prevalentSystem.setPlain(plain);
	}

}
