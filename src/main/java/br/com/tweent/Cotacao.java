package br.com.tweent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.MaskFormatter;

public class Cotacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Valor> list;
	private String csv;
	private boolean paridade = false;

	public Cotacao(String csv) {
		this.csv = csv;
	}

	private void prepare() {
		if (this.list == null && csv != null && !csv.isEmpty()) {
			this.list = new ArrayList<>();
			String[] lines = csv.split("\n");
			for (String line : lines) {
				String[] colunms = line.split(";");

				Valor valor = new Valor(colunms);
				
				if (valor.hasParidade()) {
					this.paridade = true;
				}

				list.add(valor);
			}
		}
	}

	public boolean hasParidade() {
		this.prepare();
		return paridade;
	}

	public List<Valor> getList() {
		this.prepare();
		return list;
	}

	@Override
	public String toString() {
		return this.csv;
	}

	public static class Valor implements Serializable {
		private static final long serialVersionUID = 2L;
		
		private String date;
		private String valorCompraTaxa;
		private String valorVendaTaxa;
		private String valorCompraParidade;
		private String valorVendaParidade;

		public Valor(String[] colunms) {
			try {
				MaskFormatter formatter = new MaskFormatter("##/##/####"); 
				formatter.setValueContainsLiteralCharacters(false); 

				date = (String) formatter.valueToString(colunms[0]);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			valorCompraTaxa = colunms[4];
			valorVendaTaxa = colunms[5];

			valorCompraParidade = colunms[6];
			valorVendaParidade = colunms[7];
		}

		public String getValorCompraParidade() {
			return valorCompraParidade;
		}

		public String getValorCompraTaxa() {
			return valorCompraTaxa;
		}

		public String getValorVendaParidade() {
			return valorVendaParidade;
		}

		public String getValorVendaTaxa() {
			return valorVendaTaxa;
		}
		
		public boolean hasParidade() {
			return !this.getValorCompraParidade().equals("1,0000") || !this.getValorVendaParidade().equals("1,0000"); 
		}

		public String getDate() {
			return date;
		}
	}
}
