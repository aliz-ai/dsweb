package com.doctusoft.dsw.client.gwt;

import java.util.List;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.InputTagsModel;
import com.doctusoft.dsw.client.comp.model.InputTagsModel_;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class InputTagsRenderer extends BaseComponentRenderer {
	
	private boolean changedFromModel = false;
	
	private boolean changedFromWidget = false;
	
	public InputTagsRenderer(final InputTagsModel inputTagsModel) {
		super(JQuery.select("<input type=\"text\" />"), inputTagsModel);
		widget.attr("placeholder","Add-tags");
		widget.attr("data-role", "tagsinput");
		
		initTagsInput(widget);
		
		new ListBindingListener<String>(Bindings.obs(inputTagsModel).get((ObservableProperty) InputTagsModel_._defaultTagList)) {
			@Override
			public void inserted(ObservableList<String> list, int index,
					String element) {
				setDefaultTags(widget, tagListToString(list));
			}

			@Override
			public void removed(ObservableList<String> list, int index,
					String element) {
				setDefaultTags(widget, tagListToString(list));				
			}
		};
		
		new ListBindingListener<String>(Bindings.obs(inputTagsModel).get((ObservableProperty) InputTagsModel_._tagList)) {


			@Override
			public void inserted(ObservableList<String> list, int index, String element) {
				if (changedFromWidget) {
					return;
				}
				changedFromModel = true;
				addTag(widget, element);
				changedFromModel = false;
			}

			@Override
			public void removed(ObservableList<String> list, int index,	String element) {
				if (changedFromWidget) {
					return;
				}
				changedFromModel = true;
				removeTag(widget, element);
				changedFromModel = false;
			}
		};
		
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				if (changedFromModel) {
					return;
				}
				List<String> widgetTags = getWidgetTagList(widget.val());
				ObservableList<String> modelTags = inputTagsModel.getTagList();
				List<String> tagsToAdd = Lists.newArrayList();
				List<String> tagsToRemove = Lists.newArrayList();
				
				for (String widgetTag : widgetTags) {
					if (!modelTags.contains(widgetTag)) {
						changedFromWidget = true;
						tagsToAdd.add(widgetTag);
						changedFromWidget = false;
					}
				}
				if (!tagsToAdd.isEmpty()) {
					modelTags.addAll(tagsToAdd);
				}
				for (String modelTag : modelTags) {
					if (!widgetTags.contains(modelTag)) {
						tagsToRemove.add(modelTag);
					}
				}
				if (!tagsToRemove.isEmpty()) {
					changedFromWidget = true;
					modelTags.removeAll(tagsToRemove);
					changedFromWidget = false;
				}
			}
		});
		
	}
	
	private native static void initTagsInput(JQuery element) /*-{
		setTimeout(function () { element.tagsinput(); }, 10);
	}-*/;
	
	
	private native static void setDefaultTags(JQuery element, String defaultTags) /*-{
		setTimeout(function () { 
			element.tagsinput('destroy');
			element.tagsinput({
				typeahead: {
					source: defaultTags.split(","),
					freeInput: true
				}
			}); 
		}, 10);
	}-*/;
	
	private native static void addTag(JQuery element, String newTag) /*-{
		element.tagsinput('add', newTag);
	}-*/;

	private native static void removeTag(JQuery element, String removeTag) /*-{
		element.tagsinput('remove', removeTag);
	}-*/;
	
	List<String> getWidgetTagList(String widgetTags) {
		List<String> tagList = Lists.newArrayList();
		for (String s : widgetTags.split(",")) {
			if (!Strings.isNullOrEmpty(s) && !s.trim().isEmpty()) {
				tagList.add(s);
			}
		}
		return tagList; 
	}
	
	String tagListToString(List<String> tagList) {
		String tagString = "";
		for (String tag : tagList) {
			tagString = tagString + tag + ",";
		}
		if (tagString.length() > 0) {
			tagString = tagString.substring(0, tagString.length()-1);
		}
		return tagString;
	}
	
}

