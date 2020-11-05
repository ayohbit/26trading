package br.com.tweent;

import java.io.Serializable;

public class Infraero implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String imagem;
	
	public Infraero(String imagem) {
		this.imagem = imagem;
	}
	
	@Override
	public String toString() {
		return this.imagem;
	}
}
