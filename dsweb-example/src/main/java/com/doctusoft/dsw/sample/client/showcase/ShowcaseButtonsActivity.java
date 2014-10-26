package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseButtonsActivity extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseButtonsActivity>{

	@Getter
	private ViewOf<ShowcaseButtonsActivity> view;
	
	public ShowcaseButtonsActivity(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseButtonsView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseButtonsActivity> implements Serializable {
		public Place() {
			super("showcasebuttons", ShowcaseButtonsActivity.class );
		}
	}

}
