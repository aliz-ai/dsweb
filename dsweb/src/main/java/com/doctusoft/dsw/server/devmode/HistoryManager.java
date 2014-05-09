package com.doctusoft.dsw.server.devmode;

import java.util.Map;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.HistoryHandler;
import com.doctusoft.dsw.client.comp.HistoryHandler_;
import com.doctusoft.gwt.light.mvp.IPlaceController;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.WithTokenizers;
import com.google.gwt.thirdparty.guava.common.collect.Maps;

public class HistoryManager {
	
	private Map<String, PlaceTokenizer<?>> tokenizerMap = Maps.newHashMap();
	private Place defaultPlace;
	
	public HistoryManager(HistoryHandler historyHandler, final IPlaceController placeController, Class<? extends PlaceHistoryMapper> mapperClass, Place defaultPlace) {
		this.defaultPlace = defaultPlace;
		WithTokenizers tokenizers = mapperClass.getAnnotation(WithTokenizers.class);
		for (Class<? extends PlaceTokenizer<?>> cls: tokenizers.value()) {
			try {
				PlaceTokenizer<?> tokenizer = cls.newInstance();
				String clsName = cls.getName();
				// xx.MyPlace$Tokenizer
				clsName = clsName.substring(0, clsName.lastIndexOf('$'));
				// xx.MyPlace
				clsName = clsName.substring(clsName.lastIndexOf('.') + 1);
				// MyPlace
				tokenizerMap.put(clsName, tokenizer);
			} catch (Exception e) {
				throw new RuntimeException("could not instantiate Tokenizer: " + cls, e);
			}
		}
		HistoryHandler_._historyToken.addChangeListener(historyHandler, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				Place place = parsePlace(newValue);
				placeController.goTo(place);
			}
		});
	}
	
	protected Place parsePlace(String token) {
		if (token == null)
			return defaultPlace;
		String [] terms = token.split(":");
		PlaceTokenizer<?> tokenizer = tokenizerMap.get(terms[0]);
		if (tokenizer == null)
			return defaultPlace;
		String param = null;
		if (terms.length > 1) {
			param = terms[1];
		}
		Object place = tokenizer.getPlace(param);
		return (Place) place;
	}

}
