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


import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.sample.client.BaseShowcaseView;

public class ShowcaseExceptionsView extends BaseShowcaseView<ShowcaseExceptionsPresenter> {
	
	public ShowcaseExceptionsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
			.add(new HtmlContent("<h1>Exceptions</h1>"));
		new Button("Dangerous button")
			.click(presenterMethod(ShowcaseExceptionsPresenter_.__dangerousMethod))
			.appendTo(subContainer);
		new HtmlContent("<hr/>").appendTo(subContainer);
		new Button("Cause sync error")
			.click(new EmptyEventHandler() {
				@Override
				public void handle() {
					throw new RuntimeException("sync error caused");
				}
			})
			.appendTo(subContainer);
	}

}
