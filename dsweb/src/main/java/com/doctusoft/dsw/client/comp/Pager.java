package com.doctusoft.dsw.client.comp;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.bean.binding.ValueBinding;

public class Pager extends Composite<BaseContainer> {

	@ObservableProperty
	private Integer activePage = 1;

	@ObservableProperty
	private Integer numberOfPages = 1;

	private Label pageInfoLabel;
	private Link previousButton;
	private Link nextButton;

	private EmptyEventHandler event;

	public Pager() {
		super(new BaseContainer("ul"));		
		root.addStyleClass("pager");
		
		/*
		 * Previous button
		 */
		previousButton = new Link("«").click(new EmptyEventHandler() {

			@Override
			public void handle() {
				int page = getActivePage();
				if (page > 1){
					page--;
					setActivePage(page);
					if (event != null) {
						 event.handle();
					}
				}
			}
		});
		new BaseContainer("li").add(previousButton).appendTo(root);

		/*
		 * Pageinfo
		 */
		pageInfoLabel = new Label();
		new BaseContainer("li").add(pageInfoLabel).appendTo(root);

		/*
		 * Next button
		 */
		nextButton = new Link("»").click(new EmptyEventHandler() {

			@Override
			public void handle() {
				int page = getActivePage();
				if (page < getNumberOfPages()){
					page++;
					setActivePage(page);
					if (event != null) {
						 event.handle();
					}
				}
			}
		});
		new BaseContainer("li").add(nextButton).appendTo(root);

		Bindings.obs(this).get(Pager_._activePage)
				.addValueChangeListener(new ValueChangeListener<Integer>() {
					@Override
					public void valueChanged(Integer newValue) {
						pageInfoLabel.model.setLabel(newValue + "/"
								+ getNumberOfPages());
					}
				});

		Bindings.obs(this).get(Pager_._numberOfPages)
				.addValueChangeListener(new ValueChangeListener<Integer>() {
					@Override
					public void valueChanged(Integer newValue) {
						pageInfoLabel.model.setLabel(getActivePage() + "/"
								+ newValue);
					}
				});
	}
	
	public Pager onPaging(final EmptyEventHandler handler) {
		event = handler;
		return this;
	}

	public Pager bindActivePage(final ValueBinding<Integer> activePageBinding) {
		Bindings.bind(activePageBinding,
				Bindings.obs(this).get(Pager_._activePage));
		return this;
	}

	public Pager bindNumberOfPages(final ValueBinding<Integer> activePageBinding) {
		Bindings.bind(activePageBinding,
				Bindings.obs(this).get(Pager_._numberOfPages));
		return this;
	}

}
