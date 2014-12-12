package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.common.collect.Lists;

public class ShowcaseTypeaheadPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseTypeaheadPresenter>{

	@Getter
	private ViewOf<ShowcaseTypeaheadPresenter> view;

	@ObservableProperty
	private List<SelectItem<String>> stringOptions =
	SelectItems.fromStrings("First item", "Second item", "Third item", "Fourth item",
			"Fifth item", "Sixth item", "Septimo dia", "Huiteme truc",
			"Ninth something", "Tenth teeth");

	@ObservableProperty
	private String value;

	@ObservableProperty
	private String remoteValue;

	@ObservableProperty
	private String queryString;

	@ObservableProperty
	private List<String> options = Lists.newArrayList();

	public ShowcaseTypeaheadPresenter(final Place place, final ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseTypeaheadView();

		Bindings.obs(this).get(ShowcaseTypeaheadPresenter_._queryString).addValueChangeListener(new ValueChangeListener<String>() {

			private String oldValue = null;

			@Override
			public void valueChanged(final String newValue) {
				if(newValue == null) {
					return;
				}

				if(!newValue.equals(oldValue)) {
					oldValue = newValue;

					int newValueLength = newValue.length();
					if(newValueLength > 2) {
						List<String> tempList = createFakeSelectList(queryString, newValueLength);
						setOptions(tempList);
					} else {
						List<String> tempList = Lists.newArrayList();
						setOptions(tempList);
					}

				}
			}
		});

	}

	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseTypeaheadPresenter> implements Serializable {
		public Place() {
			super("showcasetypeahead", ShowcaseTypeaheadPresenter.class );
		}
	}

	private static List<String> createFakeSelectList(final String beginWith, final int length) {
		List<String> fakeList = Lists.newArrayList();

		for(int i = 0; i < 20; i++) {
			fakeList.add(beginWith + length + i);
		}

		return fakeList;
	}

}
