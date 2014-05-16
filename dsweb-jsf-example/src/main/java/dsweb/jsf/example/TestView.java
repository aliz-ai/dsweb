package dsweb.jsf.example;

import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.jsf.AbstractBackingView;

public class TestView extends AbstractBackingView<TestBacking> {
	
	public TestView() {
		super(TestBacking.class, "TestBacking");
		new Label().bind(bindOnPresenter().get(TestBacking_._message)).appendTo(container);
	}

}
