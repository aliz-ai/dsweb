package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Datepicker;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class ShowcaseDatepickerView extends ContainerWithPresenter<ShowcaseActivity> {

	public ShowcaseDatepickerView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
		.add(new HtmlContent("<h1>Datepicker</h1>"));
		
		new Datepicker().bind( bindOnPresenter().get( ShowcaseActivity_._timeTest ) )
		.withFormat( "yyyy.mm.dd" )
		.appendTo(container);
	}
	
	

}
