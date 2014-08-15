package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.PagerModel;
import com.doctusoft.dsw.client.comp.model.PagerModel_;

public class Pager extends AbstractContainer<Pager, PagerModel> {

	private Label pageInfoLabel;
	private Link previousButton;
	private Link nextButton;

	public Pager() {
		super(new PagerModel());
		model.setElementType("ul class='pager'");
		
		/*
		 * Previous button
		 */
		previousButton = new Link("«").click(new EmptyEventHandler() {
			
			@Override
			public void handle() {
				int page = model.getActivePage();
				if (page > 1) {
					page--;
					model.setActivePage(page);
				}
			}
		});
		new BaseContainer("li").add(previousButton).appendTo(this);
		
		/*
		 * Pageinfo
		 */
		pageInfoLabel = new Label();
		new BaseContainer("li").add(pageInfoLabel).appendTo(this);
		
		/*
		 * Next button
		 */
		nextButton = new Link("»").click(new EmptyEventHandler() {
			
			@Override
			public void handle() {
				int page = model.getActivePage();
				if (page < model.getNumberOfPages()) {
					page++;
					model.setActivePage(page);
				}
			}
		});
		new BaseContainer("li").add(nextButton).appendTo(this);
		
		Bindings.obs(model).get(PagerModel_._activePage).addValueChangeListener(new ValueChangeListener<Integer>() {
			@Override
			public void valueChanged(Integer newValue) {
				pageInfoLabel.model.setLabel(newValue +"/" + model.getNumberOfPages());
			}
		});
		
		Bindings.obs(model).get(PagerModel_._numberOfPages).addValueChangeListener(new ValueChangeListener<Integer>() {
			@Override
			public void valueChanged(Integer newValue) {
				pageInfoLabel.model.setLabel(model.getActivePage() + "/" + newValue);
			}
		});
	}

	public Pager bindActivePage(final ValueBinding<Integer> activePageBinding) {
		Bindings.bind(activePageBinding, Bindings.obs(model).get(PagerModel_._activePage));
		return this;
	}

	public Pager bindNumberOfPages(final ValueBinding<Integer> activePageBinding) {
		Bindings.bind(activePageBinding, Bindings.obs(model).get(PagerModel_._numberOfPages));
		return this;
	}
}
