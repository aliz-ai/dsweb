
package com.doctusoft.dsw.sample.client.person;

import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Datepicker;
import com.doctusoft.dsw.client.comp.InputTags;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class SandboxView extends ContainerWithPresenter<SandboxActivity> {
	
	private Select<String> select;
	private InputTags inputTagsWithOption;
	private InputText focus;

	public SandboxView() {
		
//		inputTagsWithOption = new InputTags()
//			.bindTagOption(bindOnPresenter().get(SandboxActivity_._tagOptions))
//			.bindTagOptionSuggestions(bindOnPresenter().get(SandboxActivity_._tagOptionSuggestions));
//		new Label("Glob")
//			.bindVisible(bindOnPresenter().get(SandboxActivity_._visibility));
//
//		new InputTags()
//			.bind(bindOnPresenter().get(SandboxActivity_._tags))
//			.bindTagSuggestions(bindOnPresenter().get(SandboxActivity_._tagSuggestions))
//			.appendTo(container);
		
		//		new Alert("blup", "Blip")
		//			.bindVisible(bindOnPresenter().get(SandboxActivity_._visibility))
		//			.appendTo(container);
		//		
		//		new InputTags()
		//			.bind(bindOnPresenter().get(SandboxActivity_._tags))
		//			.bindTagSuggestions(bindOnPresenter().get(SandboxActivity_._defaultTags))
		//			.appendTo(container);
		//		
		//		select = (Select<String>) new Select<String>()
		//				.bind(bindOnPresenter().get(SandboxActivity_._test))
		//				.appendTo(container); 
		
		new Datepicker()
			.bind( bindOnPresenter().get( SandboxActivity_._test ) )
			.withFormat( "yyyy.mm.dd" )
			.appendTo( container );
		
		Button checkButton = new Button("check");
		checkButton.click(presenterMethod(SandboxActivity_.__checkBindings));
		container.add(checkButton);
//		
//		new Button("whoosh")
//			.click(presenterMethod(SandboxActivity_.__changeVisibility))
//			.appendTo(container);
		new InputText().bindFocus(bindOnPresenter().get(SandboxActivity_._focus));
		focus = new InputText().appendTo(container).bindFocus(bindOnPresenter().get(SandboxActivity_._focus));
	}
	
	@Override
	public void viewPresented() {
		focus.focus();
//		select.setSelectItems(getPresenter().getLocationItems());
	}
	
}
