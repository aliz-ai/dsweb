package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.HtmlContentModel;
import com.doctusoft.dsw.client.comp.model.HtmlContentModel_;
import com.xedge.jquery.client.JQuery;

public class HtmlContentRenderer extends BaseComponentRenderer {
	
	public HtmlContentRenderer(HtmlContentModel model) {
		super(JQuery.select("<span/>"), model);
		widget.html(model.getHtmlContent());
		HtmlContentModel_._htmlContent.addChangeListener(model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.html(newValue);
			}
		});
	}

}
