/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.nyt;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.web.WebBundleService;
import de.fhb.sd.web.ui.mainview.component.TopMenuBar;
import de.fhb.sd.web.ui.nyt.component.NYTMessageTable;
import de.fhb.sd.web.ui.util.MessageTableWithDetails;

/**
 * @author MacYser
 */
public class NewYorkTimesView extends CustomComponent implements View {

	/* define Layout objects */
	private VerticalSplitPanel vertical = new VerticalSplitPanel();
	/* define Components */
	private MessageTableWithDetails content;
	private TopMenuBar topMenuBar;

	public NewYorkTimesView() {
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
			content = new MessageTableWithDetails(new NYTMessageTable(((WebBundleService) UI.getCurrent()).getNyt()));
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
