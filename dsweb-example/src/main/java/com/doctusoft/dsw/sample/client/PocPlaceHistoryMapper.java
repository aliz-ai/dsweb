package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.sample.client.person.PersonDetailPlace;
import com.doctusoft.dsw.sample.client.person.PersonListPlace;
import com.doctusoft.dsw.sample.client.person.SandboxPlace;
import com.doctusoft.dsw.sample.client.showcase.ShowcasePlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ PersonListPlace.Tokenizer.class, PersonDetailPlace.Tokenizer.class, ShowcasePlace.Tokenizer.class, SandboxPlace.Tokenizer.class })
public interface PocPlaceHistoryMapper extends PlaceHistoryMapper {

}
