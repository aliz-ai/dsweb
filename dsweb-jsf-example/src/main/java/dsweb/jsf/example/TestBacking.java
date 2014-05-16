package dsweb.jsf.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlPanelGroup;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.Property;

@SessionScoped
@ManagedBean(name="TestBacking")
public class TestBacking {
	
	private HtmlPanelGroup view = null;
	
	@Property @Getter @Setter
	private String message = "hello world";
	
	public HtmlPanelGroup getView() {
		if (view == null) {
			view = (HtmlPanelGroup) new TestView().getComponent();
		}
		return view;
	}
	
	public void setView(HtmlPanelGroup view) {
		// do nothing
	}

	
}
