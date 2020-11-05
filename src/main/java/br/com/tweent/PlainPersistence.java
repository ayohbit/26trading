package br.com.tweent;

import java.io.Serializable;

public class PlainPersistence implements Serializable {

	private static final long serialVersionUID = 2L;

	private Plain plain;

	public void setPlain(Plain plain) {
		this.plain = plain;
	}
	
	public Plain getPlain() {
		return plain;
	}
}
