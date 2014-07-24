
package com.doctusoft.dsw.sample.client.person;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.BaseActivity;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ChartActivity extends BaseActivity<ChartActivity, ChartPlace> {
	
	public ChartActivity( ClientFactory clientFactory, ChartPlace place ) {
		super( clientFactory, place );
	}
	
	@Override
	protected ViewOf<ChartActivity> createView() {
		return clientFactory.getChartView();
	}
	
}
