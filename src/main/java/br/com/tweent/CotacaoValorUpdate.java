package br.com.tweent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.prevayler.Transaction;

import br.com.tweent.Cotacao.Valor;

public class CotacaoValorUpdate implements Transaction<CotacaoValorPersistence>, Serializable {

	private static final long serialVersionUID = 3L;

	private CotacaoValor cotacaoValor;
	private List<Valor> valors;
	private String codigo;

	public CotacaoValorUpdate(CotacaoValor cotacaoValor) {
		this.cotacaoValor = cotacaoValor;
	}

	public CotacaoValorUpdate(String codigo, List<Valor> valors) {
		this.codigo = codigo;
		this.valors = valors;
	}

	@Override
	public void executeOn(CotacaoValorPersistence prevalentSystem, Date executionTime) {
		if (cotacaoValor != null) {
			prevalentSystem.add(cotacaoValor);
		} else if (valors != null) {
			for (Valor valor : valors) {
				prevalentSystem.add(new CotacaoValor(valor, codigo));
			}
		}
	}

}
