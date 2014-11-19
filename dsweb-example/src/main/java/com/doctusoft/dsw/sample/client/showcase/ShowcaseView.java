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


import java.util.Map;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.Cell;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.Navs;
import com.doctusoft.dsw.client.comp.Row;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter_;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.person.ChartView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseActivity_;
import com.google.common.collect.Maps;

public class ShowcaseView extends ContainerWithPresenter<ShowcaseActivity> {

	private final Container subcontainer;
	private final Map<ShowcaseItem, HasComponentModel> subitems = Maps.newHashMap();

	public ShowcaseView() {
		Row row = new Row().appendTo(container);
		Cell menuCell = new Cell().withSpan(3).appendTo(row);
		new Navs().stacked()
		.addMenuItem(new Link("Buttons", "#showcasebuttons"))
		.addMenuItem(new Link("Datepicker", "#showcasedatepicker"))
		.addMenuItem(new Link("Select", "#showcaseselect"))
		.addMenuItem(new Link("Typeahead", "#showcasetypeahead"))
		.addMenuItem(new Link("Inputs", "#showcaseinputs"))
		.addMenuItem(new Link("Input Tags", "#showcaseinputtags"))
		.addMenuItem(new Link("Navs", "#showcasenavs"))
		.addMenuItem(new Link("Tabsheet", "#showcasetabsheet"))
		.addMenuItem(new Link("Tables", "#showcasetables"))
		.addMenuItem(new Link("Progress Bars", "#showcaseprogressbars"))
		.addMenuItem(new Link("Exceptions", "#showcaseexceptions"))
		.addMenuItem(new Link("Charts", "#showcasecharts"))
		.addMenuItem(new Link("RichText editor", "#showcaserichtexteditor"))
		.appendTo(menuCell);
		subcontainer = new Container().appendTo(new Cell().withSpan(9).appendTo(row));
		// a lazy initialization would be nicer, but that's okay for now
		subitems.put(ShowcaseItem.Buttons, new ShowcaseButtonsView());
		subitems.put(ShowcaseItem.Select, new ShowcaseSelectView());
		subitems.put(ShowcaseItem.Inputs, new ShowcaseInputsView());
		subitems.put(ShowcaseItem.Typeahead, new ShowcaseTypeaheadView());
		subitems.put(ShowcaseItem.Navs, new ShowcaseNavsView());
		subitems.put(ShowcaseItem.ProgressBars, new ShowcaseProgressBarView());
		ShowcaseRichTextEditorView showcaseRichTextEditorView = new ShowcaseRichTextEditorView();
		Bindings.bind(bindOnPresenter(),
				(ValueBinding) Bindings.obs(showcaseRichTextEditorView).get(ContainerWithPresenter_._presenter));
		subitems.put(ShowcaseItem.RichTextEditor, showcaseRichTextEditorView);

		ShowcaseTableView tableView = new ShowcaseTableView();
		Bindings.bind(bindOnPresenter(), (ValueBinding) Bindings.obs(tableView).get(ContainerWithPresenter_._presenter));
		subitems.put(ShowcaseItem.Tables, tableView);

		ShowcaseFreeInputTagsView inputTagsView = new ShowcaseFreeInputTagsView();
		Bindings.bind(bindOnPresenter(), (ValueBinding)Bindings.obs(inputTagsView).get(ContainerWithPresenter_._presenter));
		subitems.put(ShowcaseItem.InputTags, inputTagsView);

		ShowcaseDatepickerView datepickerView = new ShowcaseDatepickerView();
		Bindings.bind(bindOnPresenter(), (ValueBinding)Bindings.obs(datepickerView).get(ContainerWithPresenter_._presenter));
		subitems.put(ShowcaseItem.Datepicker, datepickerView);

		ShowcaseExceptionsView exceptionsView = new ShowcaseExceptionsView();
		Bindings.bind(bindOnPresenter(), (ValueBinding) Bindings.obs(exceptionsView).get(ContainerWithPresenter_._presenter));
		subitems.put(ShowcaseItem.Exceptions, exceptionsView);
		bindOnPresenter().get(ShowcaseActivity_._item).addValueChangeListener(new ValueChangeListener<ShowcaseItem>() {
			@Override
			public void valueChanged(ShowcaseItem item) {
				subcontainer.clear();
				HasComponentModel subView = subitems.get(item);
				if (subView instanceof  ViewOf) {
					((ViewOf<?>) subView).viewPresented();
				}
				subcontainer.add(subView);
			}
		});

		ChartView chartView = new ChartView();
		Bindings.bind(bindOnPresenter(), (ValueBinding) Bindings.obs(chartView).get(ContainerWithPresenter_._presenter));

		subitems.put( ShowcaseItem.Charts, chartView );
	}

}
