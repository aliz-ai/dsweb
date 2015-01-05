package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.mvp.AbstractPlace;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseSelectPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseSelectPresenter>{

	@Getter
	private ViewOf<ShowcaseSelectPresenter> view;

	@ObservableProperty
	private List<SelectItem<String>> selectableItems;

	@ObservableProperty
	private String selectedItem;

	@ObservableProperty
	private String nullOptionCaption;

	@ObservableProperty
	private boolean editable = true;

	public ShowcaseSelectPresenter(final Place place, final ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseSelectView();
	}

	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseSelectPresenter> implements Serializable {
		public Place() {
			super("showcaseselect", ShowcaseSelectPresenter.class );
		}
	}

	@Override
	public void start(final AbstractPlace<ShowcaseSelectPresenter> place) {
		setSelectedItem(null);
		setSelectableItems(SelectItems.fromStrings("First item","Second item","Third item"));
	}

	@MethodRef
	public void eraseNullOptionCaption() {
		setNullOptionCaption(null);
	}
}
