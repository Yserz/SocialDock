package de.fhb.sd.web.ui.twitterview.component;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.twitter.domain.TwitterMessage;
import de.fhb.sd.web.ui.util.DetailPanel;

public class TwitterDetailPanel extends DetailPanel {
	public TwitterDetailPanel(Message selectedMessage) {
		super(selectedMessage);
	}

	@Override
	protected AbstractLayout addDetailContent() {
		TwitterMessage twitterM = (TwitterMessage) selectedMessage;
		GridLayout infoGrid = new GridLayout(2, 3);
		infoGrid.setMargin(true);

		infoGrid.addComponent(new Label("Author: "));
		infoGrid.addComponent(new Label(twitterM != null ? twitterM.getAuthor() : ""));

		infoGrid.addComponent(new Label("Published: "));
		infoGrid.addComponent(new Label(twitterM != null ? twitterM.getPublished().toString() : ""));

		infoGrid.addComponent(new Label("Message: "));
		infoGrid.addComponent(new Label(twitterM != null ? twitterM.getMessage() : ""));

		return infoGrid;
	}
}
