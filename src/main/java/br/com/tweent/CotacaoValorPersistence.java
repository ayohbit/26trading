package br.com.tweent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CotacaoValorPersistence implements Serializable, Iterable<CotacaoValor> {

	private static final long serialVersionUID = 2L;

	private Set<CotacaoValor> list = new HashSet<>();

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	public Iterator<CotacaoValor> iterator() {
		return list.iterator();
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	public boolean add(CotacaoValor e) {
		return list.add(e);
	}

}
