package com.doctusoft.dsw.sample.client.person;

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


import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.doctusoft.ObservableProperty;

@NoArgsConstructor
@AllArgsConstructor
public class PersonDto implements Serializable {
	
	@ObservableProperty
	private Long id;
	
	@ObservableProperty
	private String name;
	
	@ObservableProperty
	private String email;
	
	@ObservableProperty
	private Date birthDate;

}
