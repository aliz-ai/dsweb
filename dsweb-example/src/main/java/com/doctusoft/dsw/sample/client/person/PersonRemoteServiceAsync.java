package com.doctusoft.dsw.sample.client.person;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PersonRemoteServiceAsync {

	void getPersonDtos(AsyncCallback<List<PersonDto>> callback);

	void getPerson(long personId, AsyncCallback<PersonDto> callback);

	void save(PersonDto dto, AsyncCallback<Long> callback);

	void delete(long personId, AsyncCallback<Void> callback);

}
