package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.client.comp.Cell;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.Navs;
import com.doctusoft.dsw.client.comp.Row;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public abstract class BaseShowcaseView<Presenter> extends ContainerWithPresenter<Presenter> {
	
	protected Container subContainer;
	
	public BaseShowcaseView() {
		
		Row row = new Row().appendTo(container);
		Cell menuCell = new Cell().withSpan(3).appendTo(row);
		new Navs().stacked()
		.addMenuItem(new Link("Buttons", "#showcasebuttons"))
		.addMenuItem(new Link("DatePicker", "#showcasedatepicker"))
		.addMenuItem(new Link("Select", "#showcaseselect"))
		.addMenuItem(new Link("Typeahead", "#showcasetypeahead"))
		.addMenuItem(new Link("Inputs", "#showcaseinputs"))
		.addMenuItem(new Link("Input Tags (Free)", "#showcasefreeinputtags"))
		.addMenuItem(new Link("Input Tags (Fixed)", "#showcasefixedinputtags"))
		.addMenuItem(new Link("Navs", "#showcasenavs"))
		.addMenuItem(new Link("Tabsheet", "#showcasetabsheet"))
		.addMenuItem(new Link("Tables", "#showcasetables"))
		.addMenuItem(new Link("Progress Bars", "#showcaseprogressbars"))
		.addMenuItem(new Link("Exceptions", "#showcaseexceptions"))
		.addMenuItem(new Link("Charts", "#showcasecharts"))
		.addMenuItem(new Link("RichText editor", "#showcaserichtexteditor"))
		.addMenuItem(new Link("Context Menu", "#showcasecontextmenu"))
		.appendTo(menuCell);
		
		subContainer = new Container().appendTo(new Cell().withSpan(9).appendTo(row));
		subContainer.css("width","auto");
		
	}

}
