package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.OnbeforeunloadModel;
import com.doctusoft.dsw.client.comp.model.OnbeforeunloadModel_;

/**
 * Not a real component. Supporting onbeforeunload functionality
 */
public class Onbeforeunload extends BaseComponent<Onbeforeunload, OnbeforeunloadModel> {

	public Onbeforeunload() {
		super(new OnbeforeunloadModel());
	}

	public Onbeforeunload bindOnbeforeunloadMessage(final ValueBinding<String> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(OnbeforeunloadModel_._onbeforeunloadMessage));
		return this;
	}

}
