/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.aboutview;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.web.ui.mainview.component.TopMenuBar;

/**
 *
 * @author MacYser
 */
public class AboutView extends CustomComponent implements View {

	/* define Layout objects */
	private VerticalSplitPanel vertical = new VerticalSplitPanel();
	/* define Components */
	private Label content;
	private TopMenuBar topMenuBar;

	public AboutView() {
		super();

		vertical.setSizeFull();
		vertical.setSplitPosition(3, Unit.PERCENTAGE);
		vertical.setLocked(true);

		fillLayout();

		setCompositionRoot(vertical);
		setSizeFull();
	}

	private void fillLayout() {
		topMenuBar = new TopMenuBar();
		content = new Label("About");

		vertical.addComponent(topMenuBar);
		vertical.addComponent(content);

	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		Notification.show("Entered View: " + event.getViewName());
	}
}
