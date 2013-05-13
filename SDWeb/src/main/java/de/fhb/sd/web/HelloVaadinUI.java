/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web;

//import com.vaadin.annotations.Title;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author MacYser
 */
//@CDIUI
@Title("Hello Vaadin")
@SessionScoped
@PreserveOnRefresh
public class HelloVaadinUI extends UI {

//	@Inject
//	@OSGiService(dynamic = true)
//	private TwitterLocal twitter;
	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout view = new VerticalLayout();
		view.addComponent(new Label("Hello Vaadin!"));
		setContent(view);
	}
}
