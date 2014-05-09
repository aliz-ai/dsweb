package com.doctusoft.dsw.sample.client.person;

import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class PersonDetailView extends ContainerWithPresenter<PersonDetailActivity> {
	
	public PersonDetailView() {
		new Label()
			.bind(bindOnPresenter().get(PersonDetailActivity_._personDto).get(PersonDto_._name))
			.appendTo(container);
	}

}
