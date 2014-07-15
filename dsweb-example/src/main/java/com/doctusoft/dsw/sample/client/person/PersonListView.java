package com.doctusoft.dsw.sample.client.person;

import com.doctusoft.dsw.client.comp.BaseComponent;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.ModalDialog;
import com.doctusoft.dsw.client.comp.Repeat;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;
import com.doctusoft.dsw.sample.client.custom.CustomComponent;

public class PersonListView extends ContainerWithPresenter<PersonListActivity> {

	public PersonListView() {
		final Label label = new Label("Person list:");
		label.addStyleClass("heading");
		container.add(label);
		Repeat<PersonDto> repeat = new Repeat<PersonDto>() {
			@Override
			protected BaseComponent<?, ?> renderItem(PersonDto item, int rowNum) {
				BaseContainer row = new BaseContainer();
				row.add(new Label("" + item.getId()));
				row.add(new Link(item.getName(), "#PersonDetailPlace:" + item.getId()));
				row.add(new Button("Delete").click(presenterMethod(PersonListActivity_.__deletePerson, item)));
				return row;
			}
		}.bind(bindOnPresenter().get(PersonListActivity_._personList));
		container.add(repeat);
		new Button("Add person")
				.click(presenterMethod(PersonListActivity_.__addPerson))
				.withStyleClass("btn-primary")
				.appendTo(container);
		ModalDialog dialog = new ModalDialog();
		container.add(dialog);
		dialog.withHeader("dialog heading");
		dialog.addContent(new Label("hello world"));
		dialog.addContent(new CustomComponent("custom component"));
		dialog.addFooter(new Button("Close"));
//		dialog.show();
	}

}