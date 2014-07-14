package com.doctusoft.dsw.sample.client.person;

import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class SandboxView extends ContainerWithPresenter<SandboxActivity> {
	
	private Select<String> select;

	public SandboxView() {
		
		select = (Select<String>) new Select<String>()
				.bind(bindOnPresenter().get(SandboxActivity_._test))
				.appendTo(container); 
		
		Button checkButton = new Button("check");
		checkButton.click(presenterMethod(SandboxActivity_.__checkBindings));
		container.add(checkButton);

	}
	
	@Override
	public void viewPresented() {
		select.setSelectItems(getPresenter().getLocationItems());
	}
	
	

}
