package com.pogoda;

import java.util.ArrayList;
import java.util.List;

public class ListObserver<T> {
	
	private List<ListAcceptor<T>> acceptors = new ArrayList<ListAcceptor<T>>(); 
	
	public void addAcceptor(ListAcceptor<T> acceptor) {
		acceptors.add(acceptor);
	}
	
	public void notify(ListProvider<T> provider) {
		for (ListAcceptor<T> acceptor : acceptors) {
			acceptor.accept(provider.getList());
		}
	}

}
