package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.client.mvp.AbstractPlace;
import com.doctusoft.dsw.client.mvp.PlaceFactory;
import com.doctusoft.dsw.client.mvp.Presenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseButtonsActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputsActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseRichTextEditorActivity;

public class ExamplePlaceFactory implements PlaceFactory {

	@Override
	public <P extends Presenter<P>> AbstractPlace<P> createPlaceForClass(
			Class<? extends AbstractPlace<?>> placeClass) {
		if (ShowcaseRichTextEditorActivity.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseRichTextEditorActivity.Place();
		if (ShowcaseInputsActivity.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseInputsActivity.Place();
		if (ShowcaseButtonsActivity.Place.class.equals(placeClass))
			return (AbstractPlace<P>) new ShowcaseButtonsActivity.Place();
		    
		    return null;
	}

}
