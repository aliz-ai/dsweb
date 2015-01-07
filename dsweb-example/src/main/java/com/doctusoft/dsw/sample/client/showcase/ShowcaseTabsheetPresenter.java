package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseTabsheetPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseTabsheetPresenter>{

	@Getter
	private ViewOf<ShowcaseTabsheetPresenter> view;

	@ObservableProperty
	private String modalContentValue = "";

	@ObservableProperty
	private boolean modalExampleShowed = false;

	@ObservableProperty
	private Integer activeTabIndex = 0;

	private Integer clickedTabIndex;

	private boolean firstTabClickHappened;

	public ShowcaseTabsheetPresenter(final Place place, final ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseTabsheetView();
		firstTabClickHappened = false;

		Bindings.obs(this).get(ShowcaseTabsheetPresenter_._modalExampleShowed).addValueChangeListener(new ValueChangeListener<Boolean>() {

			@Override
			public void valueChanged(final Boolean newValue) {
				if (newValue != null && !newValue && firstTabClickHappened) {
					setActiveTabIndex(clickedTabIndex);
				}
			}
		});
	}

	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseTabsheetPresenter> implements Serializable {
		public Place() {
			super("showcasetabsheet", ShowcaseTabsheetPresenter.class );
		}
	}

	@MethodRef
	public void tabClicked(final Integer tabIndex) {
		clickedTabIndex = tabIndex;
		firstTabClickHappened = true;

		setModalContentValue("You move to tab: " + tabIndex);
		setModalExampleShowed(true);
	}

}
