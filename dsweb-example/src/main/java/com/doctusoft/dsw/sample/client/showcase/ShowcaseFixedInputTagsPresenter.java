package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;
import java.util.Arrays;

import lombok.Getter;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListChangeListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.TagOption;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.doctusoft.dsw.sample.client.showcase.ExampleData.OldComputer;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class ShowcaseFixedInputTagsPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseFixedInputTagsPresenter> {

	@Getter
	private ViewOf<ShowcaseFixedInputTagsPresenter> view;

	@ObservableProperty
	private ObservableList<OldComputer> tags = new ObservableList<OldComputer>();

	@ObservableProperty
	private String tagsJoined = "";

	@ObservableProperty
	private ObservableList<TagOption<OldComputer>> tagSuggestions = new ObservableList<TagOption<OldComputer>>();

	@ObservableProperty
	private boolean editable = true;

	public ShowcaseFixedInputTagsPresenter(final Place place, final ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseFixedInputTagsView();

		tags.add(OldComputer.CommodoreAmiga);

		tagSuggestions.addAll(Lists.transform(Arrays.asList(ExampleData.OldComputer.values()), new Function<OldComputer, TagOption<OldComputer>>() {
			@Override
			public TagOption<OldComputer> apply(final OldComputer input) {
				TagOption<OldComputer> tagOption = new TagOption<OldComputer>();
				tagOption.setValue(input);
				tagOption.setName(input.getName());
				return tagOption;
			}
		}));

		new ListChangeListener(Bindings.obs(this).get(ShowcaseFixedInputTagsPresenter_._tags)) {
			@Override
			protected void changed() {
				setTagsJoined("Selected tags: " + Joiner.on(",").join(tags));
			}
		};

	}

	@MethodRef
	public void clearTags() {
		tags.clear();
	}

	@MethodRef
	public void addRandom() {
		clearTags();
		for (TagOption<OldComputer> option : tagSuggestions) {
			if (Math.random() < 0.2) {
				tags.add(option.getValue());
			}
		}
	}

	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseFixedInputTagsPresenter> implements Serializable {
		public Place() {
			super("showcasefixedinputtags", ShowcaseFixedInputTagsPresenter.class );
		}
	}

}
