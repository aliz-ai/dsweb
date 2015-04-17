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


import java.util.List;
import java.util.Map;

import com.doctusoft.bean.ValueChangeListener;
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
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Integrates the bootstrap-inputtags component: http://timschlechter.github.io/bootstrap-tagsinput/examples/
 * Supports String typed tags. Suggested tags are provided by remote call from application, 
 * but the user can enter any string.
 */
public class FixedInputTagsRemote<T> extends BaseComponent<FixedInputTagsRemote<T>, FixedInputTagsRemoteModel> {
	
	private ObservableValueBinding<? extends ObservableList<T>> valueBinding;
	private Map<String, T> optionByCaption = Maps.newHashMap();
	private Map<String, T> currentValuesByCaption = Maps.newHashMap();	// values are not necessarily among the current options
	private BidirectionalConvertingListBinder<T, TagOptionModel> listBinder;
	
	
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
		new ListBindingListener<T>(listBinding) {
			@Override
			public void inserted(ObservableList<T> list, int index, T element) {
				currentValuesByCaption.put(getOptionCaption(element), element);
			}
			@Override
			public void removed(ObservableList<T> list, int index, T element) {
				currentValuesByCaption.remove(getOptionCaption(element));
			}
		};
		Converter<T, TagOptionModel> converter = new Converter<T, TagOptionModel>() {
			@Override
			public TagOptionModel convertSource(T source) {
				return new TagOptionModel(getOptionCaption(source));
			}
			@Override
			public T convertTarget(TagOptionModel target) {
				String optionName = target.getName();
				T value = currentValuesByCaption.get(optionName);
				if (value == null) {
					value = optionByCaption.get(optionName);
				}
				return value;
			}
		};
		listBinder = new BidirectionalConvertingListBinder<T, TagOptionModel>((ObservableValueBinding) listBinding, converter, Bindings.obs(model).get(FixedInputTagsRemoteModel_._tagOptionList));
		return this;
	}
	
	protected String getOptionCaption(T optionValue) {
		return Objects.firstNonNull(optionValue, "").toString();
	}
	
	public FixedInputTagsRemote<T> bindTagSuggestions(final ObservableValueBinding<? extends List<T>> listBinding) {
		// this doesn't actually have an initial value
		listBinding.addValueChangeListener(new ValueChangeListener<List<T>>() {
			@Override
			public void valueChanged(List<T> newOptionList) {
				optionByCaption.clear();
				if (newOptionList == null) {
					model.setTagOptionSuggestions(null);
					return;
				}
				List<TagOptionModel> optionModelList = Lists.newArrayList();
				for (T optionValue : newOptionList) {
					TagOptionModel tagOptionModel = new TagOptionModel();
					tagOptionModel.setName(getOptionCaption(optionValue));
					optionModelList.add(tagOptionModel);
					optionByCaption.put(tagOptionModel.getName(), optionValue);
				}
				model.setTagOptionSuggestions(optionModelList);
			}
		});
		return this;
	}
	
	public FixedInputTagsRemote<T> bindPlaceHolder(ObservableValueBinding<String> placeHolderBinding) {
		Bindings.bind(placeHolderBinding, Bindings.obs(model).get(FixedInputTagsRemoteModel_._placeHolder));
		return this;
	}

}