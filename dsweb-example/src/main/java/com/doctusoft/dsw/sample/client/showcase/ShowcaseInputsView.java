package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.InplaceText;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.PasswordField;
import com.doctusoft.dsw.client.comp.Textarea;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class ShowcaseInputsView implements HasComponentModel {
	
	private Container container = new Container();
	
	public ShowcaseInputsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Inputs</h1>"));
		new Label("Simple text input", "h3").appendTo(container);
		new InputText().appendTo(container);
		new Label("Textarea", "h3").appendTo(container);
		new Textarea().setRows(4).appendTo(container);
		new Label("Password input", "h3").appendTo(container);
		new PasswordField().appendTo(container);
		new Label("Inplace text", "h3").appendTo(container);
		new InplaceText().appendTo(container);
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}

}
