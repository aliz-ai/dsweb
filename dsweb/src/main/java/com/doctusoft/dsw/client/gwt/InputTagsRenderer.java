package com.doctusoft.dsw.client.gwt;

import java.util.Collections;
import java.util.List;

import lombok.Setter;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.InputTagsModel;
import com.doctusoft.dsw.client.comp.model.InputTagsModel_;
import com.google.common.collect.Lists;
import com.xedge.jquery.client.JQuery;

public class InputTagsRenderer extends ContainerRenderer {
	
	String lastValue = "";

	private static boolean changedFromThis = false;

	@Setter
	String removed;
	
	public InputTagsRenderer(final InputTagsModel container) {
		super(container);
		final JQuery input = widget.find("input");
		input.attr("id","this");
		input.attr("data-role", "tagsinput");
		input.attr("placeholder", "Add-tags");
		
		lastValue = container.getInputText();
				
		initTagsInput(input);
		
		InputTagsModel_._inputText.addChangeListener(container, new ValueChangeListener<String>() {
			
			/*
			 *  If an item is removed from the list bound to the tags, the 'removeTag' method needs to be called (unlike removing tags via clicking on the UI).
			 *  However this results in the tagsinput firing change events with the 'newValue' still containing the element to be removed (unlike when clicking on the UI).
			 *  The big mess below handles this difference between the changes on the UI and the changes on the model. 
			 * 
			 */
			
			@Override
			public void valueChanged(String newValue) {
				setRemoved(getTagToRemove(lastValue, newValue));
				List<String> newList = Lists.newArrayList(newValue.split(","));
				List<String> lastList = Lists.newArrayList();
				for (String s : lastValue.split(",")) {
					if (!s.equals(removed)) {
						lastList.add(s);
					}
				}
				
				Collections.sort(newList);
				Collections.sort(lastList);
				if (newList.equals(lastList) && !Lists.newArrayList(input.val().split(",")).contains(removed)) {
					setRemoved(null);
				}	
				
				if (removed != null) {
					removeTag(input, removed);
				} else {
					String added = getTagToAdd(lastValue, newValue);
					addTag(input, added);
				}
				
				lastValue = newValue;
			}
		});
	}
	
	private void appendTag(String added) {
		if (JQuery.select("span:contains(" + added + ")").length() != 0) {
			return;
		}
		JQuery tag = JQuery.select("<span class=\"tag label label-info\"/>");
		tag.text(added);
		JQuery remove = JQuery.select("<span data-role=\"remove\"></span>");
		remove.appendTo(tag);
		tag.insertAfter(widget.find("span.tag:last"));
	}
	
	private static void setChangedFromThis(Boolean value) {
		changedFromThis = value;
	}
	
	private static void test() {
		System.out.println(changedFromThis);
	}
	
	private native static void initTagsInput(JQuery jQuery) /*-{
		jQuery.tagsinput({
			typeahead: {
				source: ['test'],
			}
		});
	}-*/;
	
	private native static void addTag(JQuery jQuery, String newTag) /*-{
		jQuery.tagsinput('add', newTag);
	}-*/;

	private native static void removeTag(JQuery jQuery, String removeTag) /*-{
		jQuery.tagsinput('remove', removeTag);
	}-*/;
	
	private native static void removeAndReAdd(JQuery jQuery, String tags) /*-{
		jQuery.tagsinput('removeAll');
		jQuery.tagsinput('add', tags);
	}-*/;
	
	private String getTagToAdd(String lastTags, String newTags) {
		List<String> lastTagsList = Lists.newArrayList(lastTags.split(","));
		for (String tag : newTags.split(",")) {
			if (!lastTagsList.contains(tag)) {
				return tag;
			}
		}
		return null;
	}
	
	private String getTagToRemove(String lastTags, String newTags) {
		List<String> newTagsList = Lists.newArrayList(newTags.split(","));
		for (String tag : lastTags.split(",")) {
			if (!newTagsList.contains(tag)) {
				return tag;
			}
		}
		return null;
		
	}
	
	
}
