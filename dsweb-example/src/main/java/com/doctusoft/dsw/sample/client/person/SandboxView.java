package com.doctusoft.dsw.sample.client.person;

import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.ContextMenu;
import com.doctusoft.dsw.client.comp.DataTable;
import com.doctusoft.dsw.client.comp.InputTags;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.datatable.Columns;
import com.doctusoft.dsw.client.comp.datatable.DateFormatter;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class SandboxView extends ContainerWithPresenter<SandboxActivity> {
  
  private Select<String> select;
  private InputTags inputTagsWithOption;
  private InputText focus;
  
  public SandboxView() {
    new InputText().appendTo(container).addStyleClass("valami");
    
    new DataTable<PersonDto>()
      .addColumn(Columns.from("Id", PersonDto_._id))
      .addColumn(Columns.obs("Name", PersonDto_._name))
      .addColumn(Columns.from("Email", PersonDto_._email))
      .addColumn(Columns.from("Born", PersonDto_._birthDate).format(new DateFormatter("yyyy-MM-dd")))
      .bind(bindOnPresenter().get(SandboxActivity_._personList))
      .bindSelectionMode(bindOnPresenter().get(SandboxActivity_._selectionMode))
      .bindSelection(bindOnPresenter().get(SandboxActivity_._selection))
      .appendTo(container).addStyleClass("valami");
    
    new ContextMenu<Link>("td", "valami2")
      .addMenuItem(new Link("Kijelölt törlése").click(presenterMethod(SandboxActivity_.__hideLabel)))
      //.setConnectedObjectSelector("td")
      .appendTo(container);//.addStyleClass("valami2");
    
    new Label("hide me").bindVisible(bindOnPresenter().get(SandboxActivity_._visibility)).appendTo(container);
    new Button("új").click(presenterMethod(SandboxActivity_.__hideLabel)).appendTo(container);
  }
  
  @Override public void viewPresented() {
//		focus.focus();
//		select.setSelectItems(getPresenter().getLocationItems());
  }
  
}
