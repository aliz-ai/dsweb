package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.LinkModel;
import com.doctusoft.dsw.client.comp.model.LinkModel_;
import com.xedge.jquery.client.JQuery;

public class LinkRenderer extends BaseComponentRenderer {
	
	public LinkRenderer(LinkModel link) {
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
	}

}
