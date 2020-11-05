package br.com.tweent;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;

import jodd.jerry.Jerry;

public class NoticiaFactory {

	private Map<String, Future<String>> futureMap = new HashMap<>();
	private Map<String, Integer> mapKey = new HashMap<>();
	private Map<String, Boolean> mapUpdateKey = new HashMap<>();
	private ExecutorService executorService = Executors.newCachedThreadPool();

	public boolean hasUpdate(String codigo) {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		return (this.mapUpdateKey.containsKey(codigo) && this.mapUpdateKey.get(codigo)
				&& this.futureMap.containsKey(codigo) && this.futureMap.get(codigo).isDone())
				|| !this.futureMap.containsKey(codigo) || (this.mapKey.containsKey(codigo) && this.mapKey.get(codigo) != hour);
	}

	public Future<String> getFuture(String codigo) throws InterruptedException, ExecutionException {

		if (this.futureMap.containsKey(codigo) && this.futureMap.get(codigo).isDone()) {
			this.mapUpdateKey.put(codigo, false);
		}

		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if (!mapKey.containsKey(codigo) || mapKey.get(codigo) != hour) {

			futureMap.put(codigo, executorService.submit(new NoticiaCallable(codigo)));

			mapKey.put(codigo, hour);
			this.mapUpdateKey.put(codigo, true);

		}

		return futureMap.get(codigo);
	}

	public String get(String codigo) throws IOException, InterruptedException, ExecutionException {

		return getFuture(codigo).get();
	}

	private class NoticiaCallable implements Callable<String> {

		private String codigo;

		public NoticiaCallable(String codigo) {
			this.codigo = codigo;
		}

		@Override
		public String call() throws Exception {
			URL url = new URL("http://www.desenvolvimento.gov.br/portalmdic/sitio/rss.php?area=" + codigo);

			StringWriter writer = new StringWriter();

			IOUtils.copy(url.openStream(), writer);

			Jerry jerry = Jerry.jerry(writer.toString());
			
			String result = new String(jerry.$("channel item title:first").text().getBytes(), "UTF-8");

			return result;
		}

	}
}
