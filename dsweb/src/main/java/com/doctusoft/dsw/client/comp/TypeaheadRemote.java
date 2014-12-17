package com.doctusoft.dsw.client.comp;

import java.util.List;
import java.util.Map;

import lombok.Setter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadRemoteModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadRemoteModel_;
import com.doctusoft.dsw.client.util.DeferredFactory;
import com.doctusoft.dsw.client.util.DeferredRunnable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class TypeaheadRemote<T> extends BaseComponent<TypeaheadRemote<T>, TypeaheadRemoteModel>{

	@ObservableProperty
	private T value;

	private Map<SelectItemModel, T> itemsToModel = Maps.newHashMap();

	private TypeaheadRemoteDeferRunnable typeaheadRemoteDeferRunnable = new TypeaheadRemoteDeferRunnable();

	private DeferredRunnable deferredRunnable;

	private boolean valueChanged;

	private boolean optionsChanged;

	private boolean settingDisplayOnlyValue = false;

	public TypeaheadRemote() {
		super(new TypeaheadRemoteModel());

		Bindings.obs(model).get(TypeaheadRemoteModel_._value).addValueChangeListener(new ValueChangeListener<SelectItemModel>() {

			@Override
			public void valueChanged(final SelectItemModel newValue) {
				if (!settingDisplayOnlyValue) {
					setValue(itemsToModel.get(newValue));
				}
			}
		});

		TypeaheadRemote_._value.addChangeListener(this, new ValueChangeListener<Object>() {

			@Override
			public void valueChanged(final Object newValue) {
				valueChanged = true;

				typeaheadRemoteDeferRunnable.setValue((T) newValue);
				deferredRunnable = DeferredFactory.defer(deferredRunnable, typeaheadRemoteDeferRunnable);
			}
		});
	}

	public TypeaheadRemote<T> bindQueryString(final ValueBinding<String> queryStringBinding) {
		Bindings.bind(queryStringBinding, Bindings.obs(model).get(TypeaheadRemoteModel_._query));
		return this;
	}

	public TypeaheadRemote<T> bind(final ValueBinding<T> valueBinding) {
		Bindings.bind(valueBinding, (ObservableValueBinding) Bindings.obs(this).get(TypeaheadRemote_._value));
		return this;
	}

	public TypeaheadRemote<T> bindOptions(final ObservableValueBinding<List<SelectItem<T>>> optionsBinding) {
		optionsBinding.addValueChangeListener(new ValueChangeListener<List<SelectItem<T>>>() {

			@Override
			public void valueChanged(final List<SelectItem<T>> newValue) {
				if(newValue == null) {
					return;
				}

				optionsChanged = true;
				typeaheadRemoteDeferRunnable.setOptionsSelectItems(newValue);
				deferredRunnable = DeferredFactory.defer(deferredRunnable, typeaheadRemoteDeferRunnable);
			}
		});

		return this;
	}

	public TypeaheadRemote<T> withPlaceHolder(final String placeHolder) {
		model.setPlaceHolder(placeHolder);
		return this;
	}

	public TypeaheadRemote<T> bindPlaceHolder(final ValueBinding<String> placeHolderBinding) {
		Bindings.bind(placeHolderBinding, Bindings.obs(model).get(TypeaheadRemoteModel_._placeHolder));
		return this;
	}

	private static SelectItemModel createSelectItemModel(final String id, final String caption) {
		SelectItemModel itemModel = new SelectItemModel();

		itemModel.setId(id);
		itemModel.setCaption(caption);

		return itemModel;
	}

	@Setter
	public class TypeaheadRemoteDeferRunnable implements Runnable {

		private List<SelectItem<T>> optionsSelectItems;
		private T value;

		@Override
		public void run() {
			deferredRunnable = null;

			if (optionsChanged) {
				optionsChanged = false;

				List<SelectItemModel> options = Lists.newArrayList();

				itemsToModel.clear();
				for (SelectItem<T> item: optionsSelectItems) {
					SelectItemModel itemModel = createSelectItemModel(item.getId(), item.getCaption());

					itemsToModel.put(itemModel, item.getValue());
					options.add(itemModel);
				}

				model.setOptions(options);
			}

			if (valueChanged) {
				valueChanged = false;

				SelectItemModel selectItemModel = new SelectItemModel();
				selectItemModel.setId("");

				if (value == null) {
					selectItemModel.setCaption("");
				} else {
					selectItemModel.setCaption(value.toString());
				}

				settingDisplayOnlyValue = true;
				model.setValue(selectItemModel);
				settingDisplayOnlyValue = false;
			}

		}

	}

}
