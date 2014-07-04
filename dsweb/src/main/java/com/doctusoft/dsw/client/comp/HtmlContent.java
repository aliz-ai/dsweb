package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.HtmlContentModel;

public class HtmlContent extends BaseComponent<HtmlContent, HtmlContentModel> {
	
	public HtmlContent() {
		super(new HtmlContentModel());
	}

	public HtmlContent(String htmlContent) {
		this();
		model.setHtmlContent(htmlContent);
	}
}
