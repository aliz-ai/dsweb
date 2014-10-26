package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseSelectActivity extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseSelectActivity>{

	@Getter
	private ViewOf<ShowcaseSelectActivity> view;
	
	public ShowcaseSelectActivity(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseSelectView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseSelectActivity> implements Serializable {
		public Place() {
			super("showcaseselect", ShowcaseSelectActivity.class );
		}
	}

}
