package com.doctusoft.dsw.client.comp;

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


import java.util.Map;

import lombok.Setter;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.Converter;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.BidirectionalConvertingListBinder;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.FixedInputTagsRemoteModel;
import com.doctusoft.dsw.client.comp.model.FixedInputTagsRemoteModel_;
import com.doctusoft.dsw.client.comp.model.TagOptionModel;
import com.doctusoft.dsw.client.util.DeferredRunnable;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 * Integrates the bootstrap-inputtags component: http://timschlechter.github.io/bootstrap-tagsinput/examples/
 * Supports String typed tags. Suggested tags are provided by remote call from application, 
 * but the user can enter any string.
 */
public class FixedInputTagsRemote<T> extends BaseComponent<FixedInputTagsRemote<T>, FixedInputTagsRemoteModel> {
	
	private Map<TagOptionModel, TagOption<T>> tagOptionByModel = Maps.newHashMap();
	private Map<TagOption<T>, TagOptionModel> tagModelByOption = Maps.newHashMap();
	private Map<T, TagOptionModel> tagModelByValue = Maps.newHashMap();
	private ObservableValueBinding<? extends ObservableList<T>> valueBinding;
	private BidirectionalConvertingListBinder<T, TagOptionModel> listBinder;
	
	
	private FixedInputTagsRemoteDeferRunnable fixedInputTagsRemoteDeferRunnable = new FixedInputTagsRemoteDeferRunnable();
	private DeferredRunnable deferredRunnable;
	
	public FixedInputTagsRemote() {
		super(new FixedInputTagsRemoteModel());
	}
	
	public FixedInputTagsRemote<T> withPlaceHolder(String placeHolderText) {
		model.setPlaceHolder(placeHolderText);
		return this;
	}
	
	public FixedInputTagsRemote<T> bindQueryString(final ValueBinding<String> queryStringBinding) {
		Bindings.bind(queryStringBinding, Bindings.obs(model).get(FixedInputTagsRemoteModel_._query));
		return this;
	}

	public FixedInputTagsRemote<T> bind(final ObservableValueBinding<? extends ObservableList<T>> listBinding) {
		Preconditions.checkState(this.valueBinding == null, "Value was already bound to this component");
		this.valueBinding = listBinding;
		Converter<T, TagOptionModel> converter = new Converter<T, TagOptionModel>() {
			@Override
			public TagOptionModel convertSource(T source) {
				return tagModelByValue.get(source);
			}
			@Override
			public T convertTarget(TagOptionModel target) {
				TagOption<T> tagOption = tagOptionByModel.get(target);
				if (tagOption == null)
					return null;
				return tagOption.getValue();
			}
		};
		listBinder = new BidirectionalConvertingListBinder<T, TagOptionModel>((ObservableValueBinding) listBinding, converter, Bindings.obs(model).get(FixedInputTagsRemoteModel_._tagOptionList));
		return this;
	}
	
	public FixedInputTagsRemote<T> bindTagSuggestions(final ObservableValueBinding<? extends ObservableList<TagOption<T>>> listBinding) {
		new ListBindingListener<TagOption<T>>(listBinding) {
			@Override
			public void inserted(ObservableList<TagOption<T>> list, int index,
					TagOption<T> element) {
				TagOptionModel tagOptionModel = new TagOptionModel();
				tagOptionModel.setName(element.getName());
				tagOptionModel.setStyleClass(element.getStyleClass());
				model.getTagOptionSuggestions().add(tagOptionModel);
				tagOptionByModel.put(tagOptionModel, element);
				tagModelByOption.put(element, tagOptionModel);
				tagModelByValue.put(element.getValue(), tagOptionModel);
				if (listBinding != null) {
					// if the item was previously added to the values list, then it could not be reflected to the model, it remained null in the model list
					// TODO
//					if (valueBinding!=null){
						int valueIndex = valueBinding.getValue().indexOf(element.getValue());
						if (valueIndex >= 0) {
							listBinder.setSuspended(true);
							model.getTagOptionList().set(valueIndex, tagOptionModel);
							listBinder.setSuspended(false);
						}
//					}
				}
			}
			@Override
			public void removed(ObservableList<TagOption<T>> list, int index,
					TagOption<T> element) {
				TagOptionModel tagOptionModel = tagModelByOption.remove(element);
				tagOptionByModel.remove(tagOptionModel);
				model.getTagOptionSuggestions().remove(tagOptionModel);
				tagModelByValue.remove(element.getValue());
			}
		};
		return this;
	}
	
	public FixedInputTagsRemote<T> bindPlaceHolder(ObservableValueBinding<String> placeHolderBinding) {
		Bindings.bind(placeHolderBinding, Bindings.obs(model).get(FixedInputTagsRemoteModel_._placeHolder));
		return this;
	}
	
	@Setter
	public class FixedInputTagsRemoteDeferRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}

}