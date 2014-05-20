package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.model.LinkModel;
import com.doctusoft.dsw.client.comp.model.LinkModel_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class LinkRenderer extends BaseComponentRenderer {
	
	public LinkRenderer(final LinkModel link) {
		super(JQuery.select("<a/>"), link);
		widget.text(link.getText());
		widget.attr("href", link.getHref());
		LinkModel_._text.addChangeListener(link, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.text(newValue);
			}
		});
		LinkModel_._href.addChangeListener(link, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.attr("href", newValue);
			}
		});
		LinkModel_._actionListener.addChangeListener(link, new ValueChangeListener<EmptyEventHandler>() {
			@Override
			public void valueChanged(EmptyEventHandler newValue) {
				widget.attr("href","javacsript:;");
			}
		});
		widget.click(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				if (link.getActionListener() != null)  {
					link.getActionListener().handle();
				}
			}
		});
	}

}
