package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.TagOption;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseInputTagsActivity extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseInputTagsActivity> {
	
	@Getter
	private ViewOf<ShowcaseInputTagsActivity> view;
	
	@ObservableProperty
	private ObservableList<TagOption> tagOptions = new ObservableList<TagOption>();

	@ObservableProperty
	private ObservableList<TagOption> tagOptionSuggestions = new ObservableList<TagOption>();

	@ObservableProperty
	private ObservableList<String> tags = new ObservableList<String>();

	@ObservableProperty
	private String tagsJoined = "";

	@ObservableProperty
	private ObservableList<String> tagSuggestions = new ObservableList<String>();
	
	public ShowcaseInputTagsActivity(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseInputTagsView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseInputTagsActivity> implements Serializable {
		public Place() {
			super("showcaseinputtags", ShowcaseInputTagsActivity.class );
		}
	}


}
