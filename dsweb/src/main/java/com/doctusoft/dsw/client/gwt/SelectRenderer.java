package com.doctusoft.dsw.client.gwt;

import java.util.List;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.SelectModel;
import com.doctusoft.dsw.client.comp.model.SelectModel_;
import com.google.common.collect.Lists;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class SelectRenderer extends BaseComponentRenderer {
	
	private List<JQuery> options = Lists.newArrayList();
	
	public SelectRenderer(final SelectModel select) {
		super(JQuery.select("<select/>"), select);
		new ListBindingListener<SelectItemModel>(Bindings.obs(select).get((ObservableProperty) SelectModel_._selectItemsModel)) {
			@Override
			public void inserted(ObservableList<SelectItemModel> list, int index, SelectItemModel element) {
				JQuery option = JQuery.select("<option/>");
				option.text(element.getCaption());
				option.attr("name", element.getId());
				if (index == 0) {
					options.add(0, option);
					option.prependTo(widget);
				} else {
					option.insertAfter(options.get(index - 1));
					options.add(index, option);
				}
			}
			@Override
			public void removed(ObservableList<SelectItemModel> list, int index, SelectItemModel element) {
				// remove it from the DOM
				options.get(index).remove();
				// remove it from our registry
				options.remove(index);
			}
		};
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				int index = widget.children().index(widget.select("option:selected").get(0));
				select.setSelectedIndex(index);
			}
		});
	}

}
