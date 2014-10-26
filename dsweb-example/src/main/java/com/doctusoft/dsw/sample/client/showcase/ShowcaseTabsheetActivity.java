package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseTabsheetActivity extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseTabsheetActivity>{

	@Getter
	private ViewOf<ShowcaseTabsheetActivity> view;
	
	public ShowcaseTabsheetActivity(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseTabsheetView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseTabsheetActivity> implements Serializable {
		public Place() {
			super("showcasetabsheet", ShowcaseTabsheetActivity.class );
		}
	}

}
