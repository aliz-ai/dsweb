package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseExceptionsPresenter extends  com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseExceptionsPresenter> {
	
	@Getter
	private ViewOf<ShowcaseExceptionsPresenter> view;
	
	public ShowcaseExceptionsPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseExceptionsView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseExceptionsPresenter> implements Serializable {
		public Place() {
			super("showcaseexceptions", ShowcaseExceptionsPresenter.class );
		}
	}
	
	@MethodRef
	public void dangerousMethod() {
		throw new RuntimeException("unexpected exception");
	}

}
