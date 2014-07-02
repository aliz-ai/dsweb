package com.doctusoft.dsw.client.gwt;

import java.util.List;

import javax.swing.plaf.metal.OceanTheme;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.SelectModel_;
import com.doctusoft.dsw.client.comp.model.TypeaheadModel;
import com.google.common.collect.Lists;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class TypeaheadRenderer extends BaseComponentRenderer {
	
	private static List<String> optionCaptions = Lists.newArrayList();
	
	static TypeaheadModel typeaheadModel;
	
	public TypeaheadRenderer(final TypeaheadModel select) {
		super(JQuery.select("<input tpye=\"text\" data-provide=\"typeahead\" id=\"a\">"), select);
		init(widget);
		typeaheadModel = select;
		new ListBindingListener<SelectItemModel>(Bindings.obs(select).get((ObservableProperty) SelectModel_._selectItemsModel)) {
			@Override
			public void inserted(ObservableList<SelectItemModel> list, int index, SelectItemModel element) {
				updateOptions(widget, itemsToString(list));
			}
			@Override
			public void removed(ObservableList<SelectItemModel> list, int index, SelectItemModel element) {
				updateOptions(widget, itemsToString(list));
			} 
		};
	}
	
	private static void itemSelected(String item) {
		int index = optionCaptions.indexOf(item);
		typeaheadModel.setSelectedIndex(index);
	}
	
	private native void init(JQuery widget) /*-{
		widget.typeahead({
			minLength: 0,   
			matcher: function(item) {
        		if (this.query == '*') {
            		return true;
        		} else {
            		return item.indexOf(this.query) >= 0;
        		}
    		},
		    //avoid highlightning of "*"
		    highlighter: function(item) {
		        return "<div>" + item + "</div>"
		    },
			updater: function(item) {
				$entry(@com.doctusoft.dsw.client.gwt.TypeaheadRenderer::itemSelected(Ljava/lang/String;)(item));
			}
		});
		widget.click(function(event) {
			widget.val('*');
			widget.typeahead('lookup');
			widget.val('');
	 	});
	}-*/;
	
	private native void updateOptions(JQuery widget, String options) /*-{
		widget.data("typeahead").source = options.split(",");
	}-*/;
	
	private String itemsToString(List<SelectItemModel> items) {
		String options = "";
		for (int i = 0; i < items.size(); i++) {
			options = options + items.get(i).getCaption();
			if (i != items.size()-1) {
				options +=  ",";
			}
		}
		optionCaptions.clear();
		for (SelectItemModel item : items) {
			optionCaptions.add(item.getCaption());
		}
		return options;
	}
}
