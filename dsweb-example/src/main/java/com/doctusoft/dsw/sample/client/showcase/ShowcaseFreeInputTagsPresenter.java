package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListChangeListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.common.base.Joiner;

public class ShowcaseFreeInputTagsPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseFreeInputTagsPresenter> {
	
	@Getter
	private ViewOf<ShowcaseFreeInputTagsPresenter> view;
	
	@ObservableProperty
	private ObservableList<String> tags = new ObservableList<String>();

	@ObservableProperty
	private String tagsJoined = "";

	@ObservableProperty
	private ObservableList<String> tagSuggestions = new ObservableList<String>();
	
	public ShowcaseFreeInputTagsPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseFreeInputTagsView();

		tagSuggestions.addAll(ExampleData.oldComputerNames);
		
		new ListChangeListener(Bindings.obs(this).get(ShowcaseFreeInputTagsPresenter_._tags)) {
			@Override
			protected void changed() {
				setTagsJoined("Selected tags: " + Joiner.on(",").join(tags));
			}
		};
	}
	
	@MethodRef
	public void clearTags() {
		tags.clear();
	}

	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseFreeInputTagsPresenter> implements Serializable {
		public Place() {
			super("showcasefreeinputtags", ShowcaseFreeInputTagsPresenter.class );
		}
	}

}
