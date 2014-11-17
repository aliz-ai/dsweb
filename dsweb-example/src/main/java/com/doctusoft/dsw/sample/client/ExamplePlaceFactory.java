package com.doctusoft.dsw.sample.client;

import java.io.Serializable;

import com.doctusoft.dsw.client.mvp.AbstractPlace;
import com.doctusoft.dsw.client.mvp.PlaceFactory;
import com.doctusoft.dsw.client.mvp.Presenter;
import com.doctusoft.dsw.sample.client.person.PersonDetailPresenter;
import com.doctusoft.dsw.sample.client.person.PersonListPresenter;
import com.doctusoft.dsw.sample.client.sandbox.SandboxPresenter;
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

public class ExamplePlaceFactory implements PlaceFactory, Serializable {

	@Override
	public <P extends Presenter<P>> AbstractPlace<P> createPlaceForClass(
			Class<? extends AbstractPlace<?>> placeClass) {
		if (ShowcaseButtonsPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseButtonsPresenter.Place();
		if (ShowcaseDatepickerPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseDatepickerPresenter.Place();
		if (ShowcaseExceptionsPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseExceptionsPresenter.Place();		
		if (ShowcaseInputsPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseInputsPresenter.Place();
		if (ShowcaseInputTagsPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseInputTagsPresenter.Place();
		if (ShowcaseNavsPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseNavsPresenter.Place();
		if (ShowcaseProgressBarPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseProgressBarPresenter.Place();
		if (ShowcaseRichTextEditorPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseRichTextEditorPresenter.Place();
		if (ShowcaseSelectPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseSelectPresenter.Place();
		if (ShowcaseTablePresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseTablePresenter.Place();
		if (ShowcaseChartsPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseChartsPresenter.Place();
		if (ShowcaseTabsheetPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseTabsheetPresenter.Place();
		if (ShowcaseTypeaheadPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseTypeaheadPresenter.Place();
		if (ShowcaseContextMenuPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseContextMenuPresenter.Place();
		if (PersonListPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new PersonListPresenter.Place();
		if (PersonDetailPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new PersonDetailPresenter.Place();
		if (SandboxPresenter.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new SandboxPresenter.Place();
		return null;
	}

}
