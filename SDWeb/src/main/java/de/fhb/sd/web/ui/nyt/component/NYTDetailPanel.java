package de.fhb.sd.web.ui.nyt.component;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.domain.entity.NewYorkTimesMessage;
import de.fhb.sd.web.ui.util.DetailPanel;

public class NYTDetailPanel extends DetailPanel {

	public NYTDetailPanel(Message selectedMessage) {
		super(selectedMessage);
	}

	@Override
	protected Panel addDetailContent() {
		Panel detailPanel = new Panel();
		detailPanel.setSizeFull();

		GridLayout infoGrid = new GridLayout(2, 7);

		if (selectedMessage != null) {
			NewYorkTimesMessage nytM = (NewYorkTimesMessage) selectedMessage;
			if (!nytM.getMediaURL().isEmpty()) {
				ExternalResource resource = new ExternalResource(nytM.getMediaURL());
				Image img = new Image();
				img.setSource(resource);
				infoGrid.addComponent(img);
				infoGrid.addComponent(new Label(""));
			}

			infoGrid.addComponent(new Label("Title: "));
			Label titleLabel = new Label(nytM.getTitle());
			infoGrid.addComponent(titleLabel);

			infoGrid.addComponent(new Label("Author: "));
			Label authorLabel = new Label(nytM.getAuthor());
			infoGrid.addComponent(authorLabel);

			infoGrid.addComponent(new Label("Section: "));
			Label sectionLabel = new Label(nytM.getSection());
			infoGrid.addComponent(sectionLabel);

			infoGrid.addComponent(new Label("Published: "));
			Label publishedLabel = new Label(nytM.getPublished() + "");
			infoGrid.addComponent(publishedLabel);

			infoGrid.addComponent(new Label("Message: "));
			Label messageLabel = new Label(nytM.getMessage());
			infoGrid.addComponent(messageLabel);

            ExternalResource linkResource = new ExternalResource(nytM.getURL());
            infoGrid.addComponent(new Label("URL: "));
            Link urlLink = new Link(nytM.getURL(), linkResource);
            infoGrid.addComponent(urlLink);

		}
		detailPanel.setContent(infoGrid);
		return detailPanel;
	}
}
