package com.doctusoft.dsw.client.gwt;

/*
 * #%L
 * dsweb
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


import java.util.List;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.FreeInputTagsModel;
import com.doctusoft.dsw.client.comp.model.FreeInputTagsModel_;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class FreeInputTagsRenderer extends BaseComponentRenderer {

	private boolean changedFromModel = false;

	private boolean changedFromWidget = false;

	private FreeInputTagsModel inputTagsModel;

	public FreeInputTagsRenderer(final FreeInputTagsModel inputTagsModel) {
		super(JQuery.select("<input type=\"text\" />"), inputTagsModel);
		this.inputTagsModel = inputTagsModel;
		widget.attr("data-role", "tagsinput");

		firstInit(widget);
	}

	public void nativeInitialized() {
		new ListBindingListener<String>(Bindings.obs(inputTagsModel).get((ObservableProperty) FreeInputTagsModel_._tagList)) {
			@Override
			public void inserted(final ObservableList<String> list, final int index, final String element) {
				if (changedFromWidget) {
					return;
				}
				changedFromModel = true;
				addTag(widget, element);
				changedFromModel = false;
			}

			@Override
			public void removed(final ObservableList<String> list, final int index,	final String element) {
				if (changedFromWidget) {
					return;
				}
				changedFromModel = true;
				removeTag(widget, element);
				changedFromModel = false;
			}
		};

		new ListBindingListener<String>(Bindings.obs(inputTagsModel).get((ObservableProperty) FreeInputTagsModel_._tagSuggestions)) {
			@Override
			public void inserted(final ObservableList<String> list, final int index, final String element) {
				setTagSuggestions(widget, tagListToString(list));
			}

			@Override
			public void removed(final ObservableList<String> list, final int index, final String element) {
				setTagSuggestions(widget, tagListToString(list));
			}
		};

		addChangeListenerAndApply(FreeInputTagsModel_._placeHolder, inputTagsModel, new ValueChangeListener<String>() {

			@Override
			public void valueChanged(final String newValue) {
				widget.attr("placeholder", Objects.firstNonNull(newValue, ""));
				widget.next().find("input").attr("placeholder", Objects.firstNonNull(newValue, ""));
			}
		});

		widget.change(new EventHandler() {
			@Override
			public void eventComplete(final JQEvent event, final JQuery currentJQuery) {
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

		new BaseInputTagsEnabledAttributeRenderer(widget, inputTagsModel, this);
	}

	private native void firstInit(final JQuery element) /*-{
		var that = this;
		setTimeout(function () {
			element.tagsinput({
				typeahead: {
					freeInput: true
				}
			});
			that.@com.doctusoft.dsw.client.gwt.FreeInputTagsRenderer::nativeInitialized()();
		}, 1);
	}-*/;

	private native static void setTagSuggestions(final JQuery element, final String tagSuggestions) /*-{
		element.tagsinput('destroy');
		element.tagsinput({
			typeahead: {
				source: tagSuggestions.split(","),
				freeInput: true
			}
		});
	}-*/;

	private native static void addTag(final JQuery element, final String newTag) /*-{
		element.tagsinput('add', newTag);
	}-*/;

	private native static void removeTag(final JQuery element, final String removeTag) /*-{
		element.tagsinput('remove', removeTag);
	}-*/;

	List<String> getWidgetTagList(final String widgetTags) {
		List<String> tagList = Lists.newArrayList();
		for (String s : widgetTags.split(",")) {
			if (!Strings.isNullOrEmpty(s) && !s.trim().isEmpty()) {
				tagList.add(s.replaceAll("^\\s+|\\s+$", ""));
			}
		}
		return tagList;
	}

	String tagListToString(final List<String> tagList) {
		String tagString = "";
		for (String tag : tagList) {
			tagString = tagString + tag.replaceAll("^\\s+|\\s+$", "") + ",";
		}
		if (tagString.length() > 0) {
			tagString = tagString.substring(0, tagString.length()-1);
		}
		return tagString;
	}


}

