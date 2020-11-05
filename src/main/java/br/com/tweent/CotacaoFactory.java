package br.com.tweent;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;
import org.prevayler.Prevayler;

import br.com.tweent.Cotacao.Valor;

public class CotacaoFactory {
	
	private static long update = 1;

	private Map<String, Future<Cotacao>> futures = new HashMap<>();
	private Map<String, Cotacao> cotacoes = new HashMap<>();
	private Map<String, Integer> updates = new HashMap<>();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private ExecutorService executorService = Executors.newCachedThreadPool();

	public static long getUpdate() {
		return update;
	}
	
	public Cotacao get(String codigo) throws IOException, InterruptedException, ExecutionException {
		Date now = new Date();
		Calendar beforeCalendar = Calendar.getInstance();
		beforeCalendar.setTime(now);
		beforeCalendar.add(Calendar.DATE, -3);
		Date before = beforeCalendar.getTime();
		int hour = Calendar.getInstance().get(Calendar.HOUR);

		if (!updates.containsKey(codigo) || updates.get(codigo) != hour) {
			
			Future<Cotacao> future = executorService.submit(new CotacaoCallable(codigo, now, before));
			
			futures.put(codigo, future);

			updates.put(codigo, hour);
		}
		
		Set<String> keySet = futures.keySet();
		
		try {
			for (String key : keySet) {
				Future<Cotacao> future = futures.get(key);
				
				if (future.isDone()) {
					
					cotacoes.put(key, future.get());
				}
			}
		} catch (Exception e) {
			if (e instanceof UnknownHostException) {
				System.err.print(e.getMessage());
			}
		}
		
		return cotacoes.get(codigo);
	}
	
	private class CotacaoCallable implements Callable<Cotacao> {

		private String codigo;
		private Date now, before;
		
		public CotacaoCallable(String codigo, Date now, Date before) {
			this.codigo = codigo;
			this.now = now;
			this.before = before;
		}
		
		@Override
		public Cotacao call() throws Exception {
			StringBuilder builder = new StringBuilder(
					"https://ptax.bcb.gov.br/ptax_internet/consultaBoletim.do?method=gerarCSVFechamentoMoedaNoPeriodo");
			builder.append("&");
			builder.append("ChkMoeda").append("=").append(codigo);
			builder.append("&");
			builder.append("DATAINI").append("=").append(dateFormat.format(before));
			builder.append("&");
			builder.append("DATAFIM").append("=").append(dateFormat.format(now));
			
			URL url = new URL(builder.toString());
			
			StringWriter writer = new StringWriter();
			IOUtils.copy(url.openStream(), writer);
			
			Cotacao cotacao = new Cotacao(writer.toString()); 
			Prevayler<CotacaoValorPersistence> open = PersistenceFactory.open(new CotacaoValorPersistence());
			
			synchronized (open) {
				List<Valor> list = cotacao.getList();
				
				for (Valor valor : list) {
					CotacaoValor cotacaoValor = new CotacaoValor(valor, codigo);
					open.execute(new CotacaoValorUpdate(cotacaoValor));
				}
				
//				open.close();
				
				update = new Date().getTime();
			}
			
			
			return cotacao;
		}
		
	}
}
