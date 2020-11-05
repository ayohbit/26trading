package br.com.tweent;

import java.io.StringWriter;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;

import jodd.jerry.Jerry;

public class CambioFactory {

	private Future<String> future;
	private ExecutorService executorService = Executors.newCachedThreadPool();
	private int hour;
	private boolean update = false;
	
	public boolean hasUpdate() {
		int hour = Calendar.getInstance().get(Calendar.HOUR);
		
		return (this.update && this.future != null && this.future.isDone()) || this.future == null || hour != this.hour;
	}

	public Future<String> getFuture() {
		if (this.future != null && this.future.isDone()) {
			this.update = false;
		}
		
		int hour = Calendar.getInstance().get(Calendar.HOUR);

		if (hour != this.hour) {
			future = executorService.submit(new CambioCallable());
			this.hour = hour;
			this.update = true;
		}

		return future;
	}

	public String get() throws InterruptedException, ExecutionException {

		return getFuture().get();
	}

	public static class CambioCallable implements Callable<String> {

		@Override
		public String call() throws Exception {
			StringBuilder builder = new StringBuilder("http://www.bloomberg.com.br/");

			URL url = new URL(builder.toString());

			StringWriter writer = new StringWriter();
			IOUtils.copy(url.openStream(), writer);

			Jerry jerry = Jerry.jerry(writer.toString());

			String array = jerry.$("#divTicker-country-site + script").text();

			return array.replace("var stockticker_data_countrysite = ", "").replace("\t", "").replace("\n", "")
					.replace("\"}];", "\"}]");
		}

	}
}
