package br.com.tweent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.prevayler.Prevayler;

public class InfraeroFactory {

	private Future<Infraero> future;
	private ExecutorService executorService = Executors.newCachedThreadPool();

	public Infraero get() throws Exception {
		if (future == null) {
			Prevayler<InfraeroPersistence> open = PersistenceFactory.open(new InfraeroPersistence());
			future = executorService.submit(new InfraeroCallabel(open));
		}

		return future.get();
	}

	public synchronized void set(Infraero infraero) throws Exception {
		Prevayler<InfraeroPersistence> open = PersistenceFactory.open(new InfraeroPersistence());
		
		open.execute(new InfraeroUpdate(infraero));
		
//		open.close();
		
		future = null;
	}

	private class InfraeroCallabel implements Callable<Infraero> {

		private Prevayler<InfraeroPersistence> open;
		private Infraero infraero = null;

		public InfraeroCallabel(Prevayler<InfraeroPersistence> open) {
			this.open = open;
		}

		@Override
		public Infraero call() throws Exception {
			synchronized (open) {
				InfraeroPersistence prevalentSystem = open.prevalentSystem();
				infraero = prevalentSystem.getInfraero();
//				open.close();
			}

			return infraero;
		}

	}
}
