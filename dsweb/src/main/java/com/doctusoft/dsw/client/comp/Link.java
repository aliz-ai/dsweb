package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.LinkModel;
import com.doctusoft.dsw.client.comp.model.LinkModel_;

public class Link extends BaseComponent<Link, LinkModel> {
	
	public Link() {
		super(new LinkModel());
	}
	
	public Link(String text, String href) {
		this();
		model.setText(text);
		model.setHref(href);
	}
	
	public Link(String text) {
		this();
		model.setText(text);
	}
	
	public Link withText(String text) {
		model.setText(text);
		return this;
	}
	
	public Link withHref(String href) {
		model.setHref(href);
		return this;
	}
	
	public Link withTarget(String target) {
		model.setTarget(target);
		return this;
	}
	
	public Link newWindow() {
		return withTarget("_blank");
	}
	
	public Link bindText(ValueBinding<String> textBinding) {
		Bindings.bind(textBinding, Bindings.obs(model).get(LinkModel_._text));
		return this;
	}
	
	public Link bindHref(ValueBinding<String> hrefBinding) {
		Bindings.bind(hrefBinding, Bindings.obs(model).get(LinkModel_._href));
		return this;
	}
	
}
