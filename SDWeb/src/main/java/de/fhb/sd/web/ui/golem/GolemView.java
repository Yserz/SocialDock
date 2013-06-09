/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.golem;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import de.fhb.sd.web.WebBundleService;
import de.fhb.sd.web.ui.golem.component.GolemMessageTableWithDetails;
import de.fhb.sd.web.ui.mainview.component.TopMenuBar;

/**
 * @author MacYser
 */
public class GolemView extends CustomComponent implements View {

	/* define Layout objects */
	private VerticalSplitPanel vertical = new VerticalSplitPanel();
	/* define Components */
	private GolemMessageTableWithDetails content;
	private TopMenuBar topMenuBar;

	public GolemView() {
		super();

		vertical.setSizeFull();
		vertical.setSplitPosition(3, Unit.PERCENTAGE);
		vertical.setLocked(true);

		fillLayout();

		setCompositionRoot(vertical);
		setSizeFull();
	}

	private void fillLayout() {
		vertical.removeAllComponents();
		topMenuBar = new TopMenuBar();
		vertical.addComponent(topMenuBar);
		if (((WebBundleService) UI.getCurrent()).getNyt() != null) {
			content = new GolemMessageTableWithDetails(((WebBundleService) UI.getCurrent()).getGolem());
			vertical.addComponent(content);
		} else {
			vertical.addComponent(new Label("Service Unavailable"));
		}
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		Notification.show("Entered View: " + event.getViewName());
	}
}
