package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListChangeListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.TagOption;
import com.doctusoft.dsw.client.comp.TagOptions;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.common.base.Joiner;

public class ShowcaseInputTagsPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseInputTagsPresenter> {
	
	@Getter
	private ViewOf<ShowcaseInputTagsPresenter> view;
	
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
	
	public ShowcaseInputTagsPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseInputTagsView();
		init();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseInputTagsPresenter> implements Serializable {
		public Place() {
			super("showcaseinputtags", ShowcaseInputTagsPresenter.class );
		}
	}

	private void init() {
		tagSuggestions.add("Valami");
		tagOptionSuggestions.addAll( TagOptions.fromStrings( "Ez", "Az", "mi", "sas" ) );
		TagOption tagOp = new TagOption();
		tagOp.setName( "Ez már más" );
		tagOp.setStyleClass( "label label-warning" );
		TagOption tagOp2 = new TagOption();
		tagOp2.setName( "Ez már megint más" );
		tagOp2.setStyleClass( "label label-success" );
		tagOptionSuggestions.add( tagOp2 );
		tagOptionSuggestions.add( tagOp );
		new ListChangeListener(Bindings.obs(this).get(ShowcaseInputTagsPresenter_._tags)) {
			@Override
			protected void changed() {
				setTagsJoined("Selected tags: " + Joiner.on(",").join(tags));
			}
		};
	}

}
