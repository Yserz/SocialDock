package de.fhb.sd.web.ui.nyt.component;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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

		GridLayout infoGrid = new GridLayout(2, 5);
		infoGrid.setWidth(100, Unit.PERCENTAGE);
		infoGrid.setHeight(100, Unit.PERCENTAGE);

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
			titleLabel.setWidth("300px");
			infoGrid.addComponent(titleLabel);

			infoGrid.addComponent(new Label("Author: "));
			Label authorLabel = new Label(nytM.getAuthor());
			authorLabel.setWidth("300px");
			infoGrid.addComponent(authorLabel);

			infoGrid.addComponent(new Label("Section: "));
			Label sectionLabel = new Label(nytM.getSection());
			sectionLabel.setWidth("300px");
			infoGrid.addComponent(sectionLabel);

			infoGrid.addComponent(new Label("Published: "));
			Label publishedLabel = new Label(nytM.getPublished() + "");
			publishedLabel.setWidth("300px");
			infoGrid.addComponent(publishedLabel);

			infoGrid.addComponent(new Label("Message: "));
			Label messageLabel = new Label(nytM.getMessage());
			messageLabel.setWidth("300px");
			infoGrid.addComponent(messageLabel);

		}
		detailPanel.setContent(infoGrid);
		return detailPanel;
	}
}
