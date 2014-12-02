package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

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
	
	public ShowcaseTypeaheadPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseTypeaheadView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseTypeaheadPresenter> implements Serializable {
		public Place() {
			super("showcasetypeahead", ShowcaseTypeaheadPresenter.class );
		}
	}

}
