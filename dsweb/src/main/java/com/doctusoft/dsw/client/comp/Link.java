package com.doctusoft.dsw.client.comp;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.LinkModel;
import com.doctusoft.dsw.client.comp.model.LinkModel_;

public class Link extends AbstractContainer<Link, LinkModel> {
	
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
