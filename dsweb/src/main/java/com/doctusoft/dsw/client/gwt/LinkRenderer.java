package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.Link_;
import com.xedge.jquery.client.JQuery;

public class LinkRenderer extends BaseComponentRenderer {
	
	public LinkRenderer(Link link) {
		super(JQuery.select("<a/>"), link);
		widget.text(link.getText());
		widget.attr("href", link.getHref());
		Link_._text.addChangeListener(link, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.text(newValue);
			}
		});
		Link_._href.addChangeListener(link, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.attr("href", newValue);
			}
		});
	}

}
