package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.ObservableProperty;

public class Link extends BaseComponent<Link> {
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return Link_._observableProperties;
	}
	
	@ObservableProperty @Getter
	private String text;
	
	@ObservableProperty @Getter
	private String href;
	
	public Link() {
	}
	
	public Link(String text, String href) {
		setText(text);
		setHref(href);
	}
	
}
