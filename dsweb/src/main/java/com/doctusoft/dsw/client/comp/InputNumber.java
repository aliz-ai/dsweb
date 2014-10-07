package com.doctusoft.dsw.client.comp;

import java.math.BigDecimal;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.Converter;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.InputNumberModel;
import com.doctusoft.dsw.client.comp.model.InputNumberModel_;

public class InputNumber extends BaseComponent<InputNumber, InputNumberModel> {

	public InputNumber() {
		super(new InputNumberModel());
	}

	public InputNumber bind(ValueBinding<BigDecimal> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(InputNumberModel_._value));
		return this;
	}

	/**
	 * Binds the value to an Integer representation. Fraction digits are truncated
	 */
	public InputNumber bindInteger(ValueBinding<Integer> intBinding) {
		Bindings.bind(intBinding, Bindings.obs(model).get(InputNumberModel_._value).convert(new Converter<BigDecimal, Integer>() {
			@Override
			public Integer convertSource(BigDecimal source) {
				if (source == null) {
					return null;
				}
				return source.intValue();
			}
			@Override
			public BigDecimal convertTarget(Integer target) {
				if (target == null) {
					return null;
				}
				return new BigDecimal(target);
			};
		}));
		return this;
	}

	public InputNumber withPlaceHolder(String placeHolder) {
		model.setPlaceHolder(placeHolder);
		return this;
	}

}
