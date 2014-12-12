package com.doctusoft.dsw.client.comp;

import java.util.List;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.TypeaheadRemoteModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadRemoteModel_;

public class TypeaheadRemote extends BaseComponent<TypeaheadRemote, TypeaheadRemoteModel>{

	public TypeaheadRemote() {
		super(new TypeaheadRemoteModel());
	}

	public TypeaheadRemote bindQueryString(final ValueBinding<String> queryStringBinding) {
		Bindings.bind(queryStringBinding, Bindings.obs(model).get(TypeaheadRemoteModel_._query));
		return this;
	}

	public TypeaheadRemote bind(final ValueBinding<String> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(TypeaheadRemoteModel_._value));
		return this;
	}

	public TypeaheadRemote bindOptions(final ObservableValueBinding<List<String>> optionsBinding) {
		Bindings.bind(optionsBinding, Bindings.obs(model).get(TypeaheadRemoteModel_._options));
		return this;
	}

	public TypeaheadRemote withPlaceHolder(final String placeHolder) {
		model.setPlaceHolder(placeHolder);
		return this;
	}

	public TypeaheadRemote bindPlaceHolder(final ValueBinding<String> placeHolderBinding) {
		Bindings.bind(placeHolderBinding, Bindings.obs(model).get(TypeaheadRemoteModel_._placeHolder));
		return this;
	}

}
