package com.doctusoft.dsw.client.gwt;

import java.util.List;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.TagOption;
import com.doctusoft.dsw.client.comp.model.InputTagsModel;
import com.doctusoft.dsw.client.comp.model.InputTagsModel_;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class InputTagsRenderer extends BaseComponentRenderer {
	
	private boolean changedFromModel = false;
	
	private boolean changedFromWidget = false;
	
	private boolean hasTagSuggestions = false;
	
	public InputTagsRenderer(final InputTagsModel inputTagsModel) {
		super(JQuery.select("<input type=\"text\" />"), inputTagsModel);
		widget.attr("placeholder","Add-tags");
		widget.attr("data-role", "tagsinput");
		
		if (inputTagsModel.getTagList() != null) {
			addTag(widget, inputTagsModel.getTagList().toString());
		}
		new ListBindingListener<String>(Bindings.obs(inputTagsModel).get((ObservableProperty) InputTagsModel_._tagSuggestions)) {
			@Override
			public void inserted(ObservableList<String> list, int index, String element) {
				setTagSuggestions(widget, tagListToString(list));
				hasTagSuggestions = true;
			}

			@Override
			public void removed(ObservableList<String> list, int index, String element) {
				setTagSuggestions(widget, tagListToString(list));
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
		
		new ListBindingListener<TagOption>(Bindings.obs(inputTagsModel).get((ObservableProperty) InputTagsModel_._tagOptionSuggestions)) {
			@Override
			public void inserted(ObservableList<TagOption> list, int index,
					TagOption element) {
					setTagOptionSuggestions(widget, tagOptionListToString(list));
					hasTagSuggestions = true;
			}

			@Override
			public void removed(ObservableList<TagOption> list, int index,
					TagOption element) {
				setTagOptionSuggestions(widget, tagOptionListToString(list));
			}
		};
		
		new ListBindingListener<TagOption>(Bindings.obs(inputTagsModel).get((ObservableProperty) InputTagsModel_._tagOptionList)) {


			@Override
			public void inserted(ObservableList<TagOption> list, int index, TagOption element) {
				if (changedFromWidget) {
					return;
				}
				changedFromModel = true;
				addTag(widget, element);
				changedFromModel = false;
			}

			@Override
			public void removed(ObservableList<TagOption> list, int index,	TagOption element) {
				if (changedFromWidget) {
					return;
				}
				changedFromModel = true;
				removeTag(widget, element.getName());
				changedFromModel = false;
			}
		};
		
		/*
		 * we need this if we don't bind on any tag suggestion list
		 */
		if (!hasTagSuggestions) {
			setTagsIpnut(widget);
		}
		
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
						tagsToAdd.add(widgetTag);
					}
				}
				if (!tagsToAdd.isEmpty()) {
					changedFromWidget = true;
					modelTags.addAll(tagsToAdd);
					changedFromWidget = false;
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
	private native static void setTagsIpnut(JQuery element) /*-{
		setTimeout(function () { 
			element.tagsinput('destroy');
			element.tagsinput({
				typeahead: {
					freeInput: true
				}
			}); 
		}, 10);
	}-*/;
	
	private native static void setTagSuggestions(JQuery element, String tagSuggestions) /*-{
		setTimeout(function () { 
			element.tagsinput('destroy');
			element.tagsinput({
				typeahead: {
					source: tagSuggestions.split(","),
					freeInput: true
				}
			}); 
		}, 10);
	}-*/;
	
	private native static void setTagOptionSuggestions(JQuery element, JsArray<Item> tagSuggestions) /*-{
	setTimeout(function () { 
		element.tagsinput('destroy');
		element.tagsinput({
				tagClass: function(item) {
			    		return item.style;
			    },
			    itemValue: 'value',
  				itemText: 'text',
			  	typeahead: {
			    	source: tagSuggestions
			  }
		}); 
		}, 10);
	}-*/;
	
	private native static void addTag(JQuery element, String newTag) /*-{
//		setTimeout(function () { 
			element.tagsinput('add', newTag);
//		}, 1);
	}-*/;
	
	private native static void addTag(JQuery element, TagOption newTag) /*-{
		element.tagsinput('add', {value : newTag.getName(), text : newTag.getName() , style : newTag.getStyle()});
	}-*/;

	private native static void removeTag(JQuery element, String removeTag) /*-{
		element.tagsinput('remove', removeTag);
	}-*/;
	
	List<String> getWidgetTagList(String widgetTags) {
		List<String> tagList = Lists.newArrayList();
		for (String s : widgetTags.split(",")) {
			if (!Strings.isNullOrEmpty(s) && !s.trim().isEmpty()) {
				tagList.add(s.replaceAll("^\\s+|\\s+$", ""));
			}
		}
		return tagList; 
	}
	
	String tagListToString(List<String> tagList) {
		String tagString = "";
		for (String tag : tagList) {
			tagString = tagString + tag.replaceAll("^\\s+|\\s+$", "") + ",";
		}
		if (tagString.length() > 0) {
			tagString = tagString.substring(0, tagString.length()-1);
		}
		return tagString;
	}
	
	JsArray<Item> tagOptionListToString(List<TagOption> tagList) {
		JsArray<Item> array= JavaScriptObject.createArray().cast();
		for (TagOption tag : tagList) {
			Item item = (Item)JavaScriptObject.createObject().cast();
			item.setValue(tag.getName());
			item.setText(tag.getName());
			item.setStyle(tag.getStyleClass());
			array.push(item);
		}
		return array;
	}
	
	/*
	 * Helper class to build JavascriptObjects from TagOption
	 */
	static class Item extends JavaScriptObject
	{
	    protected Item(){}

	    public final native String setValue(String value)/*-{ 
	        this.value = value; 
	    }-*/;

	    public final native String setStyle(String style)/*-{ 
	        this.style = style; 
	    }-*/;
	    
	    public final native String setText(String text)/*-{ 
        	this.text = text; 
    	}-*/;

		public final native String getValue()/*-{ 
	        return this.value; 
	    }-*/;

	    public final native String getStyle()/*-{ 
	        return this.style; 
	    }-*/;
	    
	    public final native String getText()/*-{ 
        return this.text; 
    	}-*/;

	}
	
}

