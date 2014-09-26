package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.ExecuteJavascriptModel;
import com.doctusoft.dsw.client.comp.model.ExecuteJavascriptModel_;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQuery;

public class ExecuteJavascriptRenderer extends BaseComponentRenderer {
	
	public ExecuteJavascriptRenderer(final ExecuteJavascriptModel model) {
		super(JQuery.select("<span/>"), model);
		new ListBindingListener<String>(Bindings.obs(model).get(ExecuteJavascriptModel_._snippets)) {
			@Override
			public void inserted(final ObservableList<String> list, int index, String element) {
				exec(element);
				// erase the list in a deferred way. We should modify the list inside the insert listeners, that would lead far
				new Timer() {
					@Override
					public void run() {
						model.getSnippets().clear();
					}
				}.schedule(1);
			}
			@Override
			public void removed(ObservableList<String> list, int index, String element) {
				// do nothing
			}
		};
	}
	
	private static native void exec(String js) /*-{
		$wnd.eval(js);
	}-*/;

}
