package com.pogoda;

import java.util.List;

public interface ListAcceptor<T> {
	
	void accept(List<T> list);

}
