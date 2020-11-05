package br.com.tweent;

import java.io.Serializable;

import br.com.tweent.Cotacao.Valor;

public class CotacaoValor implements Serializable, Comparable<CotacaoValor>{
	private static final long serialVersionUID = 1L;
	
	private String date;
	private String valorCompraTaxa;
	private String valorVendaTaxa;
	private String valorCompraParidade;
	private String valorVendaParidade;
	private String moeda;

	public CotacaoValor(Valor valor, String moeda) {
		this.moeda = moeda;
		this.valorCompraParidade = valor.getValorCompraParidade();
		this.date = valor.getDate();
		this.valorCompraTaxa = valor.getValorCompraTaxa();
		this.valorVendaParidade = valor.getValorVendaParidade();
		this.valorVendaTaxa = valor.getValorVendaTaxa();
	}

	public String getDate() {
		return date;
	}

	public String getValorCompraTaxa() {
		return valorCompraTaxa;
	}

	public String getValorVendaTaxa() {
		return valorVendaTaxa;
	}

	public String getValorCompraParidade() {
		return valorCompraParidade;
	}

	public String getValorVendaParidade() {
		return valorVendaParidade;
	}

	public String getMoeda() {
		return moeda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((moeda == null) ? 0 : moeda.hashCode());
		result = prime * result + ((valorCompraParidade == null) ? 0 : valorCompraParidade.hashCode());
		result = prime * result + ((valorCompraTaxa == null) ? 0 : valorCompraTaxa.hashCode());
		result = prime * result + ((valorVendaParidade == null) ? 0 : valorVendaParidade.hashCode());
		result = prime * result + ((valorVendaTaxa == null) ? 0 : valorVendaTaxa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CotacaoValor other = (CotacaoValor) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (moeda == null) {
			if (other.moeda != null)
				return false;
		} else if (!moeda.equals(other.moeda))
			return false;
		if (valorCompraParidade == null) {
			if (other.valorCompraParidade != null)
				return false;
		} else if (!valorCompraParidade.equals(other.valorCompraParidade))
			return false;
		if (valorCompraTaxa == null) {
			if (other.valorCompraTaxa != null)
				return false;
		} else if (!valorCompraTaxa.equals(other.valorCompraTaxa))
			return false;
		if (valorVendaParidade == null) {
			if (other.valorVendaParidade != null)
				return false;
		} else if (!valorVendaParidade.equals(other.valorVendaParidade))
			return false;
		if (valorVendaTaxa == null) {
			if (other.valorVendaTaxa != null)
				return false;
		} else if (!valorVendaTaxa.equals(other.valorVendaTaxa))
			return false;
		return true;
	}

	@Override
	public int compareTo(CotacaoValor o) {
		return this.date.compareTo(o.date);
	}
}
