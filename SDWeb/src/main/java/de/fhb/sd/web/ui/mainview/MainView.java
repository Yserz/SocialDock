/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.mainview;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.web.WebBundleService;
import de.fhb.sd.web.ui.mainview.component.TopMenuBar;
import de.fhb.sd.web.ui.mainview.component.MessageTableWithDetails;

/**
 *
 * @author MacYser
 */
public class MainView extends CustomComponent implements View {

	private TwitterLocal twitter;
	private de.fhb.sd.api.nyt.NewYorkTimesLocal nyt;
	/* define Layout objects */
	private VerticalSplitPanel vertical = new VerticalSplitPanel();
	/* define Components */
	private MessageTableWithDetails content;
	private TopMenuBar topMenuBar;

	public MainView() {
		super();


		vertical.setSizeFull();
		vertical.setSplitPosition(3, Unit.PERCENTAGE);
		vertical.setLocked(true);



		setCompositionRoot(vertical);
		setSizeFull();
	}

	private void fillLayout() {
		topMenuBar = new TopMenuBar();
		content = new MessageTableWithDetails(twitter, nyt);

		vertical.addComponent(topMenuBar);
		vertical.addComponent(content);

	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		Notification.show("Entered View: " + event.getViewName());
		this.twitter = getMyUI().twitter;
		this.nyt = getMyUI().nyt;
		fillLayout();
	}

	private WebBundleService getMyUI() {
		return (WebBundleService) getUI();
	}
}
