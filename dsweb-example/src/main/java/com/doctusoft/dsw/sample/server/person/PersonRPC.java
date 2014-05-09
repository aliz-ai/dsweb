package com.doctusoft.dsw.sample.server.person;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.doctusoft.dsw.sample.client.person.PersonDto;
import com.doctusoft.dsw.sample.client.person.PersonRemoteService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;

public class PersonRPC extends RemoteServiceServlet implements PersonRemoteService {
	
	@Override
	public PersonDto getPerson(long personId) {
		Person person = ofy().load().type(Person.class).id(personId).now();
		return ToDto.apply(person);
	}
	
	@Override
	public List<PersonDto> getPersonDtos() {
		List<Person> personList = ofy().load().type(Person.class).list();
		return Lists.newArrayList(Lists.transform(personList, ToDto));
	}
	
	@Override
	public long save(PersonDto dto) {
		Key<Person> key = ofy().save().entity(FromDto.apply(dto)).now();
		return key.getId();
	}
	
	@Override
	public void delete(long personId) {
		ofy().delete().key(Key.create(Person.class, personId));
	}
	
	public static Function<Person, PersonDto> ToDto = new Function<Person, PersonDto>() {
		@Override
		public PersonDto apply(Person input) {
			PersonDto dto = new PersonDto();
			dto.setId(input.getId());
			dto.setName(input.getName());
			dto.setEmail(input.getEmail());
			return dto;
		}
	};

	public static Function<PersonDto, Person> FromDto = new Function<PersonDto, Person>() {
		@Override
		public Person apply(PersonDto dto) {
			Person entity = new Person();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setEmail(dto.getEmail());
			return entity;
		}
	};
}
