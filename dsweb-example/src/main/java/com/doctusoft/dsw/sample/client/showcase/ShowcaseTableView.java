package com.doctusoft.dsw.sample.client.showcase;

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


import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Composite;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.ModalDialog;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.TabSheet;
import com.doctusoft.dsw.client.comp.datatable.Columns;
import com.doctusoft.dsw.client.comp.datatable.DateFormatter;
import com.doctusoft.dsw.client.comp.datatable.SingleColumnOrderingBehaviour;
import com.doctusoft.dsw.client.comp.model.SelectionMode;
import com.doctusoft.dsw.sample.client.BaseShowcaseView;
import com.doctusoft.dsw.sample.client.person.PersonDto;
import com.doctusoft.dsw.sample.client.person.PersonDto_;

public class ShowcaseTableView extends BaseShowcaseView<ShowcaseTablePresenter> {

	public ShowcaseTableView() {
		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
		.add(new HtmlContent("<h1>Tables</h1>"));

		new TabSheet()
		.withDefaultTab("Selection", new SelectionTabContent())
		.withDefaultTab("Ordering", new OrderingTabContent())
		.withDefaultTab("Changing Columns", new ChangingColumnsTabContent())
		.appendTo(subContainer);

	}

	public class SelectionTabContent extends Composite<BaseContainer> {
		public SelectionTabContent() {
			super(new BaseContainer());
			new DataTable<PersonDto>()
			.addColumn(Columns.from("Id", PersonDto_._id))
			.addColumn(Columns.obs("Name", PersonDto_._name))
			.addColumn(Columns.from("Email", PersonDto_._email))
			.addColumn(Columns.from("Born", PersonDto_._birthDate).format(new DateFormatter("yyyy-MM-dd")))
			.addColumn(Columns.actionButton(ShowcaseTableView.this, ShowcaseTablePresenter_.__personClicked, "View"))
			.bind(bindOnPresenter().get(ShowcaseTablePresenter_._personList))
			.bindSelectionMode(bindOnPresenter().get(ShowcaseTablePresenter_._selectionMode))
			.bindSelection(bindOnPresenter().get(ShowcaseTablePresenter_._selection))
			.appendTo(root);
			new ModalDialog()
			.withHeader("Selection")
			.addContent(new Label().bind(bindOnPresenter().get(ShowcaseTablePresenter_._modalContent)))
			.bindDialogVisible(bindOnPresenter().get(ShowcaseTablePresenter_._modalVisible)).appendTo(root);
			new HtmlContent("<hr/>").appendTo(root);
			new Label("Change selection mode:").appendTo(root);
			new Select<SelectionMode>()
			.bind(bindOnPresenter().get(ShowcaseTablePresenter_._selectionMode))
			.withSelectItems(SelectItems.fromEnum(SelectionMode.values()))
			.appendTo(root);
			new Label("", "div").bind(bindOnPresenter().get(ShowcaseTablePresenter_._selectionString)).appendTo(root);
		}
	}

	public class OrderingTabContent extends Composite<BaseContainer> {
		public OrderingTabContent() {
			super(new BaseContainer());
			new Label("Note: this page presents only the UI representation of ordering. This component doesn't support automatic data ordering: the presenter has to react to changes in ordering")
			.appendTo(root);
			DataTable<PersonDto> dataTable = new DataTable<PersonDto>();
			dataTable
			.addColumn(Columns.from("Id", PersonDto_._id))
			.addColumn(Columns.obs("Name", PersonDto_._name).orderable())
			.addColumn(Columns.from("Email", PersonDto_._email).orderable())
			.addColumn(Columns.from("Born", PersonDto_._birthDate).format(new DateFormatter("yyyy-MM-dd")))
			.addColumn(Columns.actionButton(ShowcaseTableView.this, ShowcaseTablePresenter_.__personClicked, "View"))
			.bind(bindOnPresenter().get(ShowcaseTablePresenter_._personList))
			.withSelectionMode(SelectionMode.None)
			.appendTo(root);
			new SingleColumnOrderingBehaviour(dataTable)
			.bind(bindOnPresenter().get(ShowcaseTablePresenter_._ordering));

			new Label().bind(bindOnPresenter().get(ShowcaseTablePresenter_._orderingInfo))
			.appendTo(root);

			BaseContainer buttons = new BaseContainer().appendTo(root);
			new Button("Clear")
			.click(presenterMethod(ShowcaseTablePresenter_.__clearOrdering))
			.appendTo(buttons);

			new Button("Order by name")
			.click(presenterMethod(ShowcaseTablePresenter_.__orderByName))
			.appendTo(buttons);

		}
	}

	public class ChangingColumnsTabContent extends Composite<BaseContainer> {
		public ChangingColumnsTabContent() {
			super(new BaseContainer());
			new DataTable<PersonDto>()
			.bindColumns(bindOnPresenter().get(ShowcaseTablePresenter_._columnDescriptors))
			.bind(bindOnPresenter().get(ShowcaseTablePresenter_._personList))
			.bindSelectionMode(bindOnPresenter().get(ShowcaseTablePresenter_._selectionMode))
			.bindSelection(bindOnPresenter().get(ShowcaseTablePresenter_._selection))
			.appendTo(root);

			BaseContainer buttons = new BaseContainer().appendTo(root);
			new Button("Clear").click(presenterMethod(ShowcaseTablePresenter_.__clearColumns)).appendTo(buttons);
			new Button("Add email").click(presenterMethod(ShowcaseTablePresenter_.__addEmail)).appendTo(buttons);
			new Button("Remove email").click(presenterMethod(ShowcaseTablePresenter_.__removeEmail)).appendTo(buttons);
		}
	}

}
