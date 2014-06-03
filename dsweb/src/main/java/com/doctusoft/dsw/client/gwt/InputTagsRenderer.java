package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.InputTagsModel;
import com.doctusoft.dsw.client.comp.model.InputTagsModel_;
import com.xedge.jquery.client.JQuery;

public class InputTagsRenderer extends ContainerRenderer {

	public InputTagsRenderer(InputTagsModel container) {
		super(container);
		final JQuery input = widget.find("input");
		input.attr("data-role", "tagsinput");
		input.attr("placeholder", "Add-tags");
		
		initTagsInput(input);
		
		InputTagsModel_._inputText.addChangeListener(container, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				String[] newValueSplit = newValue.split(",");
				String lastValue = input.val();
				if (newValue.length() > lastValue.length()) {
					String addedTag = newValueSplit[newValueSplit.length - 1];
					addTag(widget.find("input[data-role=tagsinput]"), newValue);
				} else {
					String removedTag = getTagToRemove(lastValue, newValue);
					removeTag(input, removedTag);
				}
			}
		});
		
	}
	
	private native static void initTagsInput(JQuery jQuery) /*-{
		jQuery.tagsinput();
	}-*/;
	
	private native static void addTag(JQuery jQuery, String newTag) /*-{
		jQuery.tagsinput('add', newTag);
	}-*/;

	private native static void removeTag(JQuery jQuery, String removeTag) /*-{
		jQuery.tagsinput('remove', removeTag);
	}-*/;
	
	private String getTagToRemove(String lastTags, String newTags) {
		String[] lastTagsSplit = lastTags.split(",");
		for (String tag : lastTagsSplit) {
			if (!newTags.contains(tag)) {
				return tag;
			}
		}
		return null;
		
	}
	
	
}
