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


import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.ProgressBar;
import com.doctusoft.dsw.client.comp.ProgressBar.ProgressBarType;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class ShowcaseProgressBarView extends ContainerWithPresenter<ShowcaseProgressBarPresenter> {
	
	public ShowcaseProgressBarView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Progress Bars</h1>"));
		new Label("Basic", "h3").appendTo(container);
		new ProgressBar().appendTo(container);
		new Label("Animated", "h3").appendTo(container);
		new ProgressBar().setType(ProgressBarType.StripedActive).appendTo(container);
		new Label("Colored", "h3").appendTo(container);
		new ProgressBar().setType(ProgressBarType.Info).appendTo(container);
		new ProgressBar().setType(ProgressBarType.Success).appendTo(container);
		new ProgressBar().setType(ProgressBarType.Warning).appendTo(container);
		new ProgressBar().setType(ProgressBarType.Danger).appendTo(container);
		new Label("Striped", "h3").appendTo(container);
		new ProgressBar().setType(ProgressBarType.Info).setType(ProgressBarType.Striped).appendTo(container);
		new ProgressBar().setType(ProgressBarType.Success).setType(ProgressBarType.Striped).appendTo(container);
		new ProgressBar().setType(ProgressBarType.Warning).setType(ProgressBarType.StripedActive).appendTo(container);
		new ProgressBar().setType(ProgressBarType.Danger).setType(ProgressBarType.StripedActive).appendTo(container);
	}

}
