package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseRichTextEditorActivity extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseRichTextEditorActivity>{
	
	@Getter
	private ViewOf<ShowcaseRichTextEditorActivity> view;
	
	@ObservableProperty
	private String content2 = "<p>ohplease</p><p>hello</p>";

	@ObservableProperty
	ObservableList<String> options = new ObservableList<String>();
	
	private static int counter = 0;
	
	@MethodRef
	public void addOption() {
		options.add(1, "option" + counter++);
	}
	
	public ShowcaseRichTextEditorActivity(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseRichTextEditorView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseRichTextEditorActivity> implements Serializable {
		public Place() {
			super("showcaserichtexteditor", ShowcaseRichTextEditorActivity.class );
		}
	}
}
