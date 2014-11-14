package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseTabsheetPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseTabsheetPresenter>{

	@Getter
	private ViewOf<ShowcaseTabsheetPresenter> view;
	
	public ShowcaseTabsheetPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseTabsheetView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseTabsheetPresenter> implements Serializable {
		public Place() {
			super("showcasetabsheet", ShowcaseTabsheetPresenter.class );
		}
	}

}
