package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseSelectPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseSelectPresenter>{

	@Getter
	private ViewOf<ShowcaseSelectPresenter> view;
	
	public ShowcaseSelectPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseSelectView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseSelectPresenter> implements Serializable {
		public Place() {
			super("showcaseselect", ShowcaseSelectPresenter.class );
		}
	}

}
