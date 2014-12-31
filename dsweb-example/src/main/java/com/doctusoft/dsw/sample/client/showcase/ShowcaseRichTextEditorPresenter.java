package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseRichTextEditorPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseRichTextEditorPresenter>{

	@Getter
	private ViewOf<ShowcaseRichTextEditorPresenter> view;

	@ObservableProperty
	private String content2 = "<p>ohplease</p><p>hello</p>";

	@ObservableProperty
	ObservableList<String> options = new ObservableList<String>();

	private static int counter = 0;

	@ObservableProperty
	private boolean editable;

	@MethodRef
	public void addOption() {
		options.add(1, "option" + counter++);
	}

	public ShowcaseRichTextEditorPresenter(final Place place, final ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseRichTextEditorView();
		init();
	}

	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseRichTextEditorPresenter> implements Serializable {
		public Place() {
			super("showcaserichtexteditor", ShowcaseRichTextEditorPresenter.class );
		}
	}

	private void init() {
		options.add("Bela");
		options.add("jozsi");
		options.add("Nora");
		options.add("ELemer");
	}
}
