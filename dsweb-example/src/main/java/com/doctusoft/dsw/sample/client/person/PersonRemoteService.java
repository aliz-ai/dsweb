package com.doctusoft.dsw.sample.client.person;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("person")
public interface PersonRemoteService extends RemoteService {
	
	public List<PersonDto> getPersonDtos();
	
	public PersonDto getPerson(long personId);
	
	public long save(PersonDto dto);
	
	public void delete(long personId);

}
