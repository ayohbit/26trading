package br.com.tweent;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

public class PersistenceFactory {
	private static String path;
	private static Map<String, Prevayler<?>> map = new HashMap<>();

	public static void setPath(String path) {
		PersistenceFactory.path = path;
	}

	@SuppressWarnings("unchecked")
	public static synchronized <T> Prevayler<T> open(T persistence) throws Exception {
		
		if (!map.containsKey(persistence.getClass().getName())) {
			Prevayler<T> createPrevayler = PrevaylerFactory.createPrevayler(persistence, path+File.separator+persistence.getClass().getSimpleName());
			map.put(persistence.getClass().getName(), createPrevayler);
		}

		return (Prevayler<T>)map.get(persistence.getClass().getName());
	}
	
	public static synchronized void takeSnapshotAndClose() throws InstantiationException, IllegalAccessException, Exception {
		
		Collection<Prevayler<?>> values = map.values();
		
		for (Prevayler<?> open : values) {
			open.takeSnapshot();
			open.close();
		}
	}
}
