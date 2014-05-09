package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.LinkModel;

public class Link extends BaseComponent<Link, LinkModel> {
	
	public Link() {
		super(new LinkModel());
	}
	
	public Link(String text, String href) {
		this();
		model.setText(text);
		model.setHref(href);
	}
	
}
