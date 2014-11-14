package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseTypeaheadPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseTypeaheadPresenter>{

	@Getter
	private ViewOf<ShowcaseTypeaheadPresenter> view;
	
	public ShowcaseTypeaheadPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseTypeaheadView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseTypeaheadPresenter> implements Serializable {
		public Place() {
			super("showcasetypeahead", ShowcaseTypeaheadPresenter.class );
		}
	}

}
