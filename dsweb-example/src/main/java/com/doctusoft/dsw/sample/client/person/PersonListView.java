package com.doctusoft.dsw.sample.client.person;

/*
 * #%L
 * dsweb-example
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.doctusoft.dsw.client.comp.BaseComponent;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.ModalDialog;
import com.doctusoft.dsw.client.comp.Repeat;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;
import com.doctusoft.dsw.sample.client.custom.CustomComponent;

public class PersonListView extends ContainerWithPresenter<PersonListPresenter> {

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