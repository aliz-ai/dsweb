package com.doctusoft.dsw.sample.client.showcase;

/*
 * #%L
 * dsweb-example
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


import java.util.List;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.Typeahead;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class ShowcaseTypeaheadView extends ContainerWithPresenter<ShowcaseTypeaheadPresenter> {
	
	private Container container = new Container();
	
	public ShowcaseTypeaheadView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Typeahead</h1>"));
		new Label("Simple typeahead", "h3").appendTo(container);
		List<SelectItem<String>> stringOptions =
				SelectItems.fromStrings("First item", "Second item", "Third item", "Fourth item",
							"Fifth item", "Sixth item", "Septimo dia", "Huiteme truc",
							"Ninth something", "Tenth teeth");
		new Typeahead<String>()
			.appendTo(container)
			.setSelectItems(stringOptions);
		new Label("Typeahead with dropdown", "h3").appendTo(container);
		new Typeahead<String>()
			.showAllOnFocus()
			.appendTo(container)
			.setSelectItems(stringOptions);
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}

}
