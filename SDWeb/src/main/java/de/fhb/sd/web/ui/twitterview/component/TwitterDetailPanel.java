package de.fhb.sd.web.ui.twitterview.component;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.domain.entity.TwitterMessage;
import de.fhb.sd.web.ui.util.DetailPanel;

public class TwitterDetailPanel extends DetailPanel {

	public TwitterDetailPanel(Message selectedMessage) {
		super(selectedMessage);
	}

	@Override
	protected Panel addDetailContent() {
		Panel detailPanel = new Panel();
		detailPanel.setSizeFull();


		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setWidth(50, Unit.PERCENTAGE);
		GridLayout infoGrid = new GridLayout(2, 3);

		if (selectedMessage != null) {
			TwitterMessage twitterM = (TwitterMessage) selectedMessage;

			ExternalResource resource = new ExternalResource(twitterM.getProfileImageUrlOfUser());
			Image img = new Image();
			img.setSource(resource);

			horizontalLayout.addComponent(img);
			horizontalLayout.setWidth(110, Unit.PIXELS);

			infoGrid.addComponent(new Label("Author: "));
			Label authorLabel = new Label(twitterM.getAuthor());
			authorLabel.setWidth("300px");
			infoGrid.addComponent(authorLabel);

			infoGrid.addComponent(new Label("Published: "));
			Label publishedLabel = new Label(twitterM.getPublished() + "");
			publishedLabel.setWidth("300px");
			infoGrid.addComponent(publishedLabel);

			infoGrid.addComponent(new Label("Message: "));
			Label messageLabel = new Label(twitterM.getMessage());
			messageLabel.setWidth("300px");
			infoGrid.addComponent(messageLabel);

			horizontalLayout.addComponent(infoGrid);
		}
		detailPanel.setContent(infoGrid);
		return detailPanel;
	}
}
