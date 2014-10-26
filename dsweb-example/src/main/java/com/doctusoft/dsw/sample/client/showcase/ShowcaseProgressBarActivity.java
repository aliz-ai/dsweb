package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseProgressBarActivity extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseProgressBarActivity>{

	@Getter
	private ViewOf<ShowcaseProgressBarActivity> view;
	
	public ShowcaseProgressBarActivity(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseProgressBarView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseProgressBarActivity> implements Serializable {
		public Place() {
			super("showcaseprogressbar", ShowcaseProgressBarActivity.class );
		}
	}

}
