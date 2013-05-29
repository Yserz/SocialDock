/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.nyt;

import com.google.gson.Gson;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.api.nyt.NewYorkTimesLocal;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.web.ui.mainview.component.TopMenuBar;

import java.util.List;

/**
 * @author MacYser
 */
public class NewYorkTimesView extends CustomComponent implements View {

	/* define Layout objects */
	private VerticalSplitPanel vertical = new VerticalSplitPanel();
	/* define Components */
	private TopMenuBar topMenuBar;
	private NewYorkTimesLocal nyt;
	private Table newsTable;

	public NewYorkTimesView(final NewYorkTimesLocal nyt) {
		super();
		this.nyt = nyt;

		vertical.setSizeFull();
		vertical.setSplitPosition(3, Unit.PERCENTAGE);
		vertical.setLocked(true);

		fillLayout();

		setCompositionRoot(vertical);
		setSizeFull();
	}

	private void fillLayout() {
		topMenuBar = new TopMenuBar();

		Gson gson = new Gson();
		List<Message> messages = nyt.getMostPopular();
		newsTable = new Table("NewYorkTmes Articles");

		newsTable.addContainerProperty("Abstract", String.class, null);
		for (Message message : messages) {
			newsTable.addItem(
					new String[]{message.getMessage()}, message);
		}

		vertical.addComponent(topMenuBar);
		vertical.addComponent(newsTable);

	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		Notification.show("Entered View: " + event.getViewName());
	}
}
