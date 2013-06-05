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
	protected AbstractLayout addDetailContent() {
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
			infoGrid.addComponent(new Label(twitterM.getAuthor()));

			infoGrid.addComponent(new Label("Published: "));
			infoGrid.addComponent(new Label(twitterM.getPublished() + ""));

			infoGrid.addComponent(new Label("Message: "));
			infoGrid.addComponent(new Label(twitterM.getMessage()));

			horizontalLayout.addComponent(infoGrid);
		}
		return horizontalLayout;
	}
}
