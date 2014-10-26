package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.client.mvp.AbstractPlace;
import com.doctusoft.dsw.client.mvp.PlacePresenterMapper;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseButtonsActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputsActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseRichTextEditorActivity;

public class ExamplePlacePresenterMapper implements PlacePresenterMapper {

	private ClientFactory clientFactory;

	public ExamplePlacePresenterMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public <Presenter extends com.doctusoft.dsw.client.mvp.Presenter<Presenter>> Presenter getPresenter(
			AbstractPlace<Presenter> place) {
		if (place instanceof ShowcaseRichTextEditorActivity.Place)
			return (Presenter) new  ShowcaseRichTextEditorActivity(( ShowcaseRichTextEditorActivity.Place) place, clientFactory);
		if (place instanceof ShowcaseInputsActivity.Place)
			return (Presenter) new ShowcaseInputsActivity((ShowcaseInputsActivity.Place) place, clientFactory);
		if (place instanceof ShowcaseButtonsActivity.Place)
			return (Presenter) new ShowcaseButtonsActivity((ShowcaseButtonsActivity.Place) place, clientFactory);
//		if (place instanceof ProcessListPresenter.Place)
//			return (Presenter) new ProcessListPresenter((ProcessListPresenter.Place) place, clientFactory);
//		if (place instanceof ProcessSearchPresenter.Place)
//			return (Presenter) new ProcessSearchPresenter((ProcessSearchPresenter.Place) place, clientFactory);
//		if (place instanceof SettingsPresenter.Place)
//			return (Presenter) new SettingsPresenter((SettingsPresenter.Place) place, clientFactory);
		throw new RuntimeException("No place-presenter mapper given for: " + place);
	}

}
