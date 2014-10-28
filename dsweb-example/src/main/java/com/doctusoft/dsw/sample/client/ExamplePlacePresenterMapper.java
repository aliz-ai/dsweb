package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.client.mvp.AbstractPlace;
import com.doctusoft.dsw.client.mvp.PlacePresenterMapper;
import com.doctusoft.dsw.sample.client.person.PersonDetailPresenter;
import com.doctusoft.dsw.sample.client.person.PersonListPresenter;
import com.doctusoft.dsw.sample.client.person.SandboxPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseButtonsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseChartsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseContextMenuPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseDatepickerPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseExceptionsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputTagsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseNavsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseProgressBarPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseRichTextEditorPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseSelectPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTablePresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTabsheetPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTypeaheadPresenter;

public class ExamplePlacePresenterMapper implements PlacePresenterMapper {

	private ClientFactory clientFactory;

	public ExamplePlacePresenterMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public <Presenter extends com.doctusoft.dsw.client.mvp.Presenter<Presenter>> Presenter getPresenter(
			AbstractPlace<Presenter> place) {
		
		if (place instanceof ShowcaseButtonsPresenter.Place)
			return (Presenter) new ShowcaseButtonsPresenter((ShowcaseButtonsPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseDatepickerPresenter.Place)
			return (Presenter) new ShowcaseDatepickerPresenter((ShowcaseDatepickerPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseExceptionsPresenter.Place)
			return (Presenter) new ShowcaseExceptionsPresenter((ShowcaseExceptionsPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseInputsPresenter.Place)
			return (Presenter) new ShowcaseInputsPresenter((ShowcaseInputsPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseInputTagsPresenter.Place)
			return (Presenter) new ShowcaseInputTagsPresenter((ShowcaseInputTagsPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseNavsPresenter.Place)
			return (Presenter) new ShowcaseNavsPresenter((ShowcaseNavsPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseProgressBarPresenter.Place)
			return (Presenter) new ShowcaseProgressBarPresenter((ShowcaseProgressBarPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseRichTextEditorPresenter.Place)
			return (Presenter) new  ShowcaseRichTextEditorPresenter(( ShowcaseRichTextEditorPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseSelectPresenter.Place)
			return (Presenter) new ShowcaseSelectPresenter((ShowcaseSelectPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseTablePresenter.Place)
			return (Presenter) new ShowcaseTablePresenter((ShowcaseTablePresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseChartsPresenter.Place)
			return (Presenter) new ShowcaseChartsPresenter((ShowcaseChartsPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseTabsheetPresenter.Place)
			return (Presenter) new ShowcaseTabsheetPresenter((ShowcaseTabsheetPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseTypeaheadPresenter.Place)
			return (Presenter) new ShowcaseTypeaheadPresenter((ShowcaseTypeaheadPresenter.Place) place, clientFactory);
		if (place instanceof ShowcaseContextMenuPresenter.Place)
			return (Presenter) new ShowcaseContextMenuPresenter((ShowcaseContextMenuPresenter.Place) place, clientFactory);
		if (place instanceof SandboxPresenter.Place)
			return (Presenter) new SandboxPresenter((SandboxPresenter.Place) place, clientFactory);
		if (place instanceof PersonListPresenter.Place)
			return (Presenter) new PersonListPresenter((PersonListPresenter.Place) place, clientFactory);
		if (place instanceof PersonDetailPresenter.Place)
			return (Presenter) new PersonDetailPresenter((PersonDetailPresenter.Place) place, clientFactory);
		throw new RuntimeException("No place-presenter mapper given for: " + place);
	}

}
