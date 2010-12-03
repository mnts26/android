package com.pogoda;

import org.xml.sax.ContentHandler;

public interface ContentProvider<T> extends ListProvider<T> {

	ContentHandler getContentHandler();
}
