package com.doctusoft.dsw.client.comp;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.junit.Assert;
import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.TagOptionModel;
import com.google.common.collect.Lists;

public class TestFixedInputTagsRemote {
	
	@ObservableProperty
	private ObservableList<Person> personList;
	
	@ObservableProperty
	private List<Person> personSuggestions;
	
	@Test
	public void testValuesMaintainedInitially() {
		personList = new ObservableList<Person>();
		personSuggestions = Lists.newArrayList();
		personList.add(new Person("A"));
		personList.add(new Person("B"));
		new FixedInputTagsRemote<Person>()
			.bind(Bindings.obs(this).get(TestFixedInputTagsRemote_._personList))
			.bindTagSuggestions(Bindings.obs(this).get(TestFixedInputTagsRemote_._personSuggestions));
		assertValues("[A, B]");
	}
	
	@Test
	public void testValuesMaintainedAfterOptionsChange() {
		personList = new ObservableList<Person>();
		personSuggestions = Lists.newArrayList();
		personList.add(new Person("A"));
		personList.add(new Person("B"));
		new FixedInputTagsRemote<Person>()
			.bind(Bindings.obs(this).get(TestFixedInputTagsRemote_._personList))
			.bindTagSuggestions(Bindings.obs(this).get(TestFixedInputTagsRemote_._personSuggestions));
		setPersonSuggestions(Lists.newArrayList(new Person("C")));
		assertValues("[A, B]");
		setPersonSuggestions(Lists.<Person>newArrayList());;
		assertValues("[A, B]");
	}
	
	@Test
	public void testChooseFromOptions() {
		personList = new ObservableList<Person>();
		FixedInputTagsRemote<Person> component = new FixedInputTagsRemote<Person>()
			.bind(Bindings.obs(this).get(TestFixedInputTagsRemote_._personList))
			.bindTagSuggestions(Bindings.obs(this).get(TestFixedInputTagsRemote_._personSuggestions));
		assertValues("[]");
		setPersonSuggestions(Lists.<Person>newArrayList(new Person("A"), new Person("B")));
		component.getModel().getTagOptionList().add(new TagOptionModel("A"));
		assertValues("[A]");
	}
	
	private void assertValues(String values) {
		Assert.assertEquals(values, personList.toString());
	}

	@Getter @Setter
	@AllArgsConstructor
	public static class Person {
		private String name;
		@Override
		public String toString() {
			return name;
		}
	}

}
