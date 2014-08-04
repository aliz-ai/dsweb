package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Datepicker;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class ShowcaseDatepickerView extends ContainerWithPresenter<ShowcaseActivity> {

	public ShowcaseDatepickerView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
		.add(new HtmlContent("<h1>Datepicker</h1>"));
		
		new Datepicker().bindDate( bindOnPresenter().get(ShowcaseActivity_._dateTimeTest) )
		.withFormat( "yyyy.MM.dd" )
		.appendTo(container);
		
		new HtmlContent("<br>").appendTo(container);
		
		new Label().bind(bindOnPresenter().get(ShowcaseActivity_._timeTest)).appendTo(container);
		
		new HtmlContent("<br>").appendTo(container);
		
		new Button("Check binding").click(presenterMethod(ShowcaseActivity_.__datePickerBindingTest)).appendTo(container);
	}
	
	

}
