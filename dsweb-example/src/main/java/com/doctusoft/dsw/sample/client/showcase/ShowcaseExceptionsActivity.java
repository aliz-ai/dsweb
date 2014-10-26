package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseExceptionsActivity extends  com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseExceptionsActivity> {
	
	@Getter
	private ViewOf<ShowcaseExceptionsActivity> view;
	
	public ShowcaseExceptionsActivity(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseExceptionsView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseExceptionsActivity> implements Serializable {
		public Place() {
			super("showcaseexceptions", ShowcaseExceptionsActivity.class );
		}
	}
	
	@MethodRef
	public void dangerousMethod() {
		throw new RuntimeException("unexpected exception");
	}

}
