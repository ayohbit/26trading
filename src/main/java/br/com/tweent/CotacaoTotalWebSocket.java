package br.com.tweent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.prevayler.Prevayler;

@ServerEndpoint("/websocket/grafico")
public class CotacaoTotalWebSocket implements Runnable {

	private Session session;
	private long update = 0;

	@OnOpen
	public void start(Session session) throws FileNotFoundException {
		this.session = session;

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		
		while(true) {
			
			if (update == CotacaoFactory.getUpdate()) {
				return;
			}
			
			update = CotacaoFactory.getUpdate();
			
			if (this.session.isOpen()) {
				Prevayler<CotacaoValorPersistence> open;
				try {
					open = PersistenceFactory.open(new CotacaoValorPersistence());
				} catch (Exception e1) {
					e1.printStackTrace();
					return;
				}
				
				Set<String> labels = new LinkedHashSet<>();
				List<String> valorEuro = new ArrayList<>();
				List<String> valorDolar = new ArrayList<>();
				
				synchronized (open) {
					CotacaoValorPersistence prevalentSystem = open.prevalentSystem();
					
					List<CotacaoValor> list = new ArrayList<CotacaoValor>();
					for (CotacaoValor cotacaoValor : prevalentSystem) {
						list.add(cotacaoValor);
					}
					
					Collections.sort(list);
					
					for (CotacaoValor cotacaoValor : list) {
						labels.add(cotacaoValor.getDate());
						
						if (cotacaoValor.getMoeda().equals("222")) {
							valorEuro.add(cotacaoValor.getValorVendaTaxa());
						} else if (cotacaoValor.getMoeda().equals("61")) {
							valorDolar.add(cotacaoValor.getValorVendaTaxa());
						}
					}
					
//					try {
//						open.close();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
				}
				
				StringBuilder builder = new StringBuilder();
				builder.append("{");
				builder.append("\"labels\": [");
				
				boolean next = false;
				for (String label : labels) {
					if (next) 
						builder.append(",");
					builder.append("\"").append(label).append("\"");
					next = true;
				}
				
				builder.append("],");
				builder.append("\"datasets\": [");
				builder.append("{");
				builder.append("\"data\": [");
				next = false;
				for (String valor : valorEuro) {
					if (next) 
						builder.append(",");
					builder.append(valor.replace(",", "."));
					next = true;
				}
				builder.append("]");
				builder.append("},");
				builder.append("{");
				builder.append("\"data\": [");
				next = false;
				for (String valor : valorDolar) {
					if (next) 
						builder.append(",");
					builder.append(valor.replace(",", "."));
					next = true;
				}
				builder.append("]");
				builder.append("}");
				builder.append("]");
				builder.append("}");
				
				try {
					this.session.getBasicRemote().sendText(builder.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				break;
			}
		}
	}
}
