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
import java.util.Map;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.FixedInputTagsRemoteModel;
import com.doctusoft.dsw.client.comp.model.FixedInputTagsRemoteModel_;
import com.doctusoft.dsw.client.comp.model.TagOptionModel;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQuery;

public class FixedInputTagsRemoteRenderer extends BaseComponentRenderer {

	private boolean changedFromModel = false;

	private boolean changedFromWidget = false;

	private JavaScriptObject callbackMethod;
	
	private FixedInputTagsRemoteModel model;

	private boolean tagSuggestionsInvalidated = false;

	private Map<TagOptionModel, TagOptionItem> itemsByModel = Maps.newHashMap();
	private Map<TagOptionItem, TagOptionModel> modelsByItem = Maps.newHashMap();

	public FixedInputTagsRemoteRenderer(final FixedInputTagsRemoteModel inputTagsModel) {
		super(JQuery.select("<input type=\"text\" />"), inputTagsModel);
		this.model = inputTagsModel;
		widget.attr("data-role", "tagsinput");

		init(widget);
		
		addChangeListenerAndApply(FixedInputTagsRemoteModel_._tagOptionSuggestions, model, new ValueChangeListener<ObservableList<TagOptionModel>>() {
			private List<TagOptionModel> oldValue;

			@Override
			public void valueChanged(ObservableList<TagOptionModel> newValue) {
				if (newValue == null || callbackMethod == null) {
					return;
				}

				if (!newValue.equals(oldValue)) {
					oldValue = newValue;

					JsArray<TagOptionItem> optionSugesstions = tagOptionListToItems(model.getTagOptionSuggestions());
					invokeCallback(optionSugesstions, callbackMethod);
					callbackMethod = null;
					
				}
			}

		});
		
	}

	// called from JSNI
	public void nativeInitialized() {
		new ListBindingListener<TagOptionModel>(Bindings.obs(model).get((ObservableProperty) FixedInputTagsRemoteModel_._tagOptionSuggestions)) {
			@Override
			public void inserted(final ObservableList<TagOptionModel> list, final int index,
					final TagOptionModel element) {
				invalidateTagOptionSuggestions();
			}

			@Override
			public void removed(final ObservableList<TagOptionModel> list, final int index,
					final TagOptionModel element) {
				invalidateTagOptionSuggestions();
			}
		};

		// note that the order of tags is not maintained. The underlying JS doesn't support insertion of items
		new ListBindingListener<TagOptionModel>(Bindings.obs(model).get((ObservableProperty) FixedInputTagsRemoteModel_._tagOptionList)) {


			@Override
			public void inserted(final ObservableList<TagOptionModel> list, final int index, final TagOptionModel element) {
				// if the tag suggestions are invalidated, then we don't have to add the option, because all the items will be added later when the timer fires
				if (changedFromWidget || tagSuggestionsInvalidated) {
					return;
				}
				changedFromModel = true;
				addTagOption(element);
				changedFromModel = false;
			}

			@Override
			public void removed(final ObservableList<TagOptionModel> list, final int index,	final TagOptionModel element) {
				if (changedFromWidget || tagSuggestionsInvalidated) {
					return;
				}
				changedFromModel = true;
				removeTag(widget, getItemByModel(element));
				changedFromModel = false;
			}
		};

		addChangeListenerAndApply(FixedInputTagsRemoteModel_._placeHolder, model, new ValueChangeListener<String>() {

			@Override
			public void valueChanged(final String newValue) {
				widget.attr("placeholder", Objects.firstNonNull(newValue, ""));
				widget.next().find("input").attr("placeholder", Objects.firstNonNull(newValue, ""));
			}
		});

		new BaseInputTagsEnabledAttributeRenderer(widget, model);
	}

	private void invalidateTagOptionSuggestions() {
		if (!tagSuggestionsInvalidated) {
			tagSuggestionsInvalidated = true;
			new Timer() {
				@Override
				public void run() {
					// set suggestions
					tagSuggestionsInvalidated = false;
					setTagOptionSuggestions(widget, tagOptionListToItems(model.getTagOptionSuggestions())); 
					
					// set tags
					changedFromModel = true;
					for (TagOptionModel option : model.getTagOptionList()) {
						if (option != null) {
							addTagOption(option);
						}
					}
					changedFromModel = false;
				}
			}.schedule(1);
		}
	}

	private void addTagOption(final TagOptionModel option) {
		TagOptionItem item = getItemByModel(option);
		addTagOption(widget, item);
	}

	private TagOptionItem getItemByModel(final TagOptionModel option) {
		TagOptionItem item = itemsByModel.get(option);
		Preconditions.checkNotNull("The tag option model: " + option.getName() + " was not found in the previously set possible options");
		return item;
	}

	public void itemAdded(final TagOptionItem item) {
		if (changedFromModel) {
			return;
		}
		TagOptionModel tagOptionModel = modelsByItem.get(item);
		Preconditions.checkNotNull(tagOptionModel);
		model.getTagOptionList().add(tagOptionModel);
	}

	public void itemRemoved(final TagOptionItem item) {
		if (changedFromModel) {
			return;
		}
		TagOptionModel tagOptionModel = modelsByItem.get(item);
		Preconditions.checkNotNull(tagOptionModel);
		model.getTagOptionList().remove(tagOptionModel);
	}

	private native void init(final JQuery element) /*-{
		var that = this;
		setTimeout(function () {
			element.tagsinput({
				tagClass: function(item) {
			    		return item.style;
			    },
			    itemValue: 'value',
  				itemText: 'text'
			});
			that.@com.doctusoft.dsw.client.gwt.FixedInputTagsRemoteRenderer::nativeInitialized()();
		}, 1);
		element.on("itemAdded", function(event) {
			that.@com.doctusoft.dsw.client.gwt.FixedInputTagsRemoteRenderer::itemAdded(Lcom/doctusoft/dsw/client/gwt/TagOptionItem;)(event.item);
		});
		element.on("itemRemoved", function(event) {
			that.@com.doctusoft.dsw.client.gwt.FixedInputTagsRemoteRenderer::itemRemoved(Lcom/doctusoft/dsw/client/gwt/TagOptionItem;)(event.item);
		});
	}-*/;

	private native void setTagOptionSuggestions(final JQuery element, final JsArray<TagOptionItem> tagSuggestions) /*-{
		var that = this;
		that.deferred = element.Deferred();
		that.sugesstionsCallback = function(data){
			that.deferred.resolve(data);
		}
		element.tagsinput('destroy');
		element.tagsinput({
				tagClass: function(item) {
			    		return item.style;
			    },
			    itemValue: 'value',
  				itemText: 'text',
			  	typeahead: {
			    	source: function(query) {
						that.@com.doctusoft.dsw.client.gwt.FixedInputTagsRemoteRenderer::updateQuery(Ljava/lang/String;)(query);
						return this.when(that.deferred);
    				}
			  }
		});
		that.@com.doctusoft.dsw.client.gwt.FixedInputTagsRemoteRenderer::setCallback(Lcom/google/gwt/core/client/JavaScriptObject;)(that.sugesstionsCallback);
	}-*/;

	private native static void addTagOption(final JQuery element, final TagOptionItem item) /*-{
		element.tagsinput('add', item);
	}-*/;

	private native static void removeTag(final JQuery element, final TagOptionItem item) /*-{
		element.tagsinput('remove', item);
	}-*/;

	JsArray<TagOptionItem> tagOptionListToItems(final List<TagOptionModel> tagList) {
		itemsByModel.clear();
		modelsByItem.clear();
		JsArray<TagOptionItem> array= JavaScriptObject.createArray().cast();
		for (TagOptionModel tag : tagList) {
			TagOptionItem item = (TagOptionItem)JavaScriptObject.createObject().cast();
			item.setValue(tag.getName());
			item.setText(tag.getName());
			item.setStyle(Objects.firstNonNull(tag.getStyleClass(), "label label-info"));
			array.push(item);
			itemsByModel.put(tag, item);
			modelsByItem.put(item, tag);
		}
		return array;
	}
	
	private void setCallback(final JavaScriptObject callbackMethod) {
		this.callbackMethod = callbackMethod;
	}

	private void updateQuery(final String query) {
		model.setQuery(query);
	}

	private native void invokeCallback(final JsArray<TagOptionItem> items, final JavaScriptObject callbackMethod) /*-{
		callbackMethod(items);
	}-*/;
	
}

