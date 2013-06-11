package de.fhb.sd.web.ui.twitterview.component;

import com.vaadin.server.ExternalResource;
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

		GridLayout infoGrid = new GridLayout(2, 5);
        infoGrid.setWidth(300, Unit.PIXELS);

		if (selectedMessage != null) {
			TwitterMessage twitterM = (TwitterMessage) selectedMessage;

			ExternalResource resource = new ExternalResource(twitterM.getProfileImageUrlOfUser());
			Image img = new Image();
			img.setSource(resource);
            infoGrid.addComponent(img, 0, 0, 1, 0);

            Label component = new Label("Author: ");
            component.setWidth(50, Unit.PIXELS);
            infoGrid.addComponent(component);
			Label authorLabel = new Label(twitterM.getAuthor());
			infoGrid.addComponent(authorLabel);

			infoGrid.addComponent(new Label("Published: "));
			Label publishedLabel = new Label(twitterM.getPublished() + "");
			infoGrid.addComponent(publishedLabel);

			infoGrid.addComponent(new Label("Message: "));
			Label messageLabel = new Label(twitterM.getMessage());
			infoGrid.addComponent(messageLabel);

            ExternalResource linkResource = new ExternalResource(twitterM.getURL());
            infoGrid.addComponent(new Label("User URL: "));
            Link urlLink = new Link(twitterM.getURL(), linkResource);
            infoGrid.addComponent(urlLink);
		}
		detailPanel.setContent(infoGrid);
		return detailPanel;
	}
}
