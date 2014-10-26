package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseTypeaheadActivity extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseTypeaheadActivity>{

	@Getter
	private ViewOf<ShowcaseTypeaheadActivity> view;
	
	public ShowcaseTypeaheadActivity(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseTypeaheadView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseTypeaheadActivity> implements Serializable {
		public Place() {
			super("showcasetypeahead", ShowcaseTypeaheadActivity.class );
		}
	}

}
