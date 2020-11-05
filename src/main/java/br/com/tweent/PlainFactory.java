package br.com.tweent;

import org.prevayler.Prevayler;

public class PlainFactory {

	public synchronized Plain get() throws Exception {
		Prevayler<PlainPersistence> open = PersistenceFactory.open(new PlainPersistence());

		Plain plain = open.prevalentSystem().getPlain();
		
//		open.close();
		
		return plain;
	}

	public synchronized void set(Plain plain) throws Exception {
		Prevayler<PlainPersistence> open = PersistenceFactory.open(new PlainPersistence());

		open.execute(new PlainUpdate(plain));
		
//		open.close();

	}
}
