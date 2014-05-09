package com.doctusoft.dsw.client.comp;



public class ModalDialog extends BaseComponent<ModalDialog> {
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return ModalDialog_._observableProperties;
	}
	
	@com.doctusoft.ObservableProperty
	private String heading = "";
	
	@com.doctusoft.ObservableProperty
	private boolean dialogVisible = false;
	
	@com.doctusoft.ObservableProperty
	private Container footerContainer = new Container();
	
	@com.doctusoft.ObservableProperty
	private Container contentContainer = new  Container();

	public ModalDialog addFooter(IsComponent component) {
		footerContainer.add(component.asComponent());
		return this;
	}

	public ModalDialog addContent(IsComponent component) {
		contentContainer.add(component.asComponent());
		return this;
	}
	
	public void show() {
		setDialogVisible(true);
	}
	
	public void hide() {
		setDialogVisible(false);
	}
}
