package com.doctusoft.dsw.sample.client.showcase;

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
import com.doctusoft.dsw.sample.client.person.ChartView;
import com.google.common.collect.Maps;

public class ShowcaseView extends ContainerWithPresenter<ShowcaseActivity> {
	
	private final Container subcontainer;
	private final Map<ShowcaseItem, HasComponentModel> subitems = Maps.newHashMap();
	
	public ShowcaseView() {
		Row row = new Row().appendTo(container);
		Cell menuCell = new Cell().withSpan(3).appendTo(row);
		new Navs().stacked()
		.addMenuItem(new Link("Buttons", "#ShowcasePlace:Buttons"))
		.addMenuItem(new Link("Datepicker", "#ShowcasePlace:Datepicker"))
		.addMenuItem(new Link("Select", "#ShowcasePlace:Select"))
		.addMenuItem(new Link("Typeahead", "#ShowcasePlace:Typeahead"))
		.addMenuItem(new Link("Inputs", "#ShowcasePlace:Inputs"))
		.addMenuItem(new Link("Input Tags", "#ShowcasePlace:InputTags"))
		.addMenuItem(new Link("Navs", "#ShowcasePlace:Navs"))
		.addMenuItem(new Link("Tables", "#ShowcasePlace:Tables"))
		.addMenuItem(new Link("Progress Bars", "#ShowcasePlace:ProgressBars"))
		.addMenuItem(new Link("Exceptions", "#ShowcasePlace:Exceptions"))
		.addMenuItem(new Link("Charts", "#ShowcasePlace:Charts"))
		.appendTo(menuCell);
		subcontainer = new Container().appendTo(new Cell().withSpan(9).appendTo(row));
		// a lazy initialization would be nicer, but that's okay for now
		subitems.put(ShowcaseItem.Buttons, new ShowcaseButtonsView());
		subitems.put(ShowcaseItem.Select, new ShowcaseSelectView());
		subitems.put(ShowcaseItem.Inputs, new ShowcaseInputsView());
		subitems.put(ShowcaseItem.Typeahead, new ShowcaseTypeaheadView());
		subitems.put(ShowcaseItem.Navs, new ShowcaseNavsView());
		subitems.put(ShowcaseItem.ProgressBars, new ShowcaseProgressBarView());
		ShowcaseTableView tableView = new ShowcaseTableView();
		Bindings.bind(bindOnPresenter(), (ValueBinding) Bindings.obs(tableView).get(ContainerWithPresenter_._presenter));
		subitems.put(ShowcaseItem.Tables, tableView);
		
		ShowcaseInputTagsView inputTagsView = new ShowcaseInputTagsView();
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
				subcontainer.add(subitems.get(item));
			}
		});
		
		ChartView chartView = new ChartView();
		Bindings.bind(bindOnPresenter(), (ValueBinding) Bindings.obs(chartView).get(ContainerWithPresenter_._presenter));
		
		subitems.put( ShowcaseItem.Charts, chartView );
	}
	
}
