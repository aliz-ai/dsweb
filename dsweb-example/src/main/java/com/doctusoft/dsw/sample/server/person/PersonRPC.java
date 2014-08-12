package com.doctusoft.dsw.sample.server.person;

/*
 * #%L
 * dsweb-example
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


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
