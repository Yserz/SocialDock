/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.nyt;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.api.nyt.NewYorkTimesLocal;
import de.fhb.sd.web.WebBundleService;
import de.fhb.sd.web.ui.mainview.component.TopMenuBar;
import de.fhb.sd.web.ui.nyt.component.NYTMessageTableWithDetails;
import de.fhb.sd.web.ui.twitterview.component.TwitterMessageTableWithDetails;

/**
 * @author MacYser
 */
public class NewYorkTimesView extends CustomComponent implements View {

	private NewYorkTimesLocal nyt;
	/* define Layout objects */
	private VerticalSplitPanel vertical = new VerticalSplitPanel();
	/* define Components */
	private NYTMessageTableWithDetails content;
	private TopMenuBar topMenuBar;

	public NewYorkTimesView() {
		super();


		vertical.setSizeFull();
		vertical.setSplitPosition(3, Unit.PERCENTAGE);
		vertical.setLocked(true);



		setCompositionRoot(vertical);
		setSizeFull();
	}

	private void fillLayout() {
		topMenuBar = new TopMenuBar();
		content = new NYTMessageTableWithDetails(nyt);

		vertical.addComponent(topMenuBar);
		vertical.addComponent(content);

	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		Notification.show("Entered View: " + event.getViewName());
		this.nyt = getMyUI().nyt;
		fillLayout();
	}

	private WebBundleService getMyUI() {
		return (WebBundleService) getUI();
	}
}
