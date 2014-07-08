package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.BaseActivity;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseActivity extends BaseActivity<ShowcaseActivity, ShowcasePlace> {
	
	@ObservableProperty
	private ShowcaseItem item;
	
	public ShowcaseActivity(ClientFactory clientFactory, ShowcasePlace showcasePlace) {
		super(clientFactory, showcasePlace);
		setItem(place.getItem());
	}
	
	@Override
	protected ViewOf<ShowcaseActivity> createView() {
		return clientFactory.getShowcaseView();
	}

	@MethodRef
	public void dangerousMethod() {
		throw new RuntimeException("unexpected exception");
	}
}
