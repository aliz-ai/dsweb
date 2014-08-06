package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.AbstractContainerModel_;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.doctusoft.dsw.client.comp.model.ComponentEvent_;
import com.doctusoft.dsw.client.comp.model.LinkModel;
import com.doctusoft.dsw.client.comp.model.LinkModel_;
import com.google.common.base.Objects;
import com.xedge.jquery.client.JQuery;

public class LinkRenderer extends BaseComponentRenderer {
	
	public LinkRenderer(final LinkModel link) {
		super(JQuery.select("<a/>"), link);
		new ChildrenRenderer(widget, (ObservableValueBinding) Bindings.obs(link).get(AbstractContainerModel_._children));
		if (link.getText() != null) {
			widget.text(link.getText());
		}
		widget.attr("href", link.getHref());
		widget.attr("target", link.getTarget());
		LinkModel_._text.addChangeListener(link, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				if (newValue != null) {
					widget.text(newValue);
				}
			}
		});
		LinkModel_._href.addChangeListener(link, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.attr("href", newValue);
			}
		});
		if (link.getClicked() != null && link.getClicked().isHasListeners()) {
			clearHref();
		} else {
			Bindings.obs(link).get(BaseComponentModel_._clicked).get(ComponentEvent_._hasListeners).addValueChangeListener(new ValueChangeListener<Boolean>() {
				@Override
				public void valueChanged(Boolean newValue) {
					if (Objects.firstNonNull(newValue, false)) {
						clearHref();
					}
				}
			});
		}
	}
	
	protected void clearHref() {
		widget.attr("href","javascript:;");
	}

}
