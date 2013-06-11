package de.fhb.sd.web.ui.golem.component;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import de.fhb.sd.domain.entity.GolemMessage;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.domain.entity.NewYorkTimesMessage;
import de.fhb.sd.web.ui.util.DetailPanel;

public class GolemDetailPanel extends DetailPanel {

	public GolemDetailPanel(Message selectedMessage) {
		super(selectedMessage);
	}

	@Override
	protected Panel addDetailContent() {
		Panel panel = new Panel();
		panel.setSizeFull();
		panel.setHeight("500px");

		GridLayout infoGrid = new GridLayout(2, 5);
//		infoGrid.setWidth(100, Unit.PERCENTAGE);
//		infoGrid.setHeight(100, Unit.PERCENTAGE);

		if (selectedMessage != null) {
			GolemMessage golemM = (GolemMessage) selectedMessage;
			if (!golemM.getImageURLMedium().isEmpty()) {
				ExternalResource resource = new ExternalResource(golemM.getImageURLMedium());
				Image img = new Image();
				img.setSource(resource);
				infoGrid.addComponent(img);
				infoGrid.addComponent(new Label(""));
			}

			infoGrid.addComponent(new Label("Title: "));
			Label titleLabel = new Label(golemM.getTitle());
			titleLabel.setWidth("300px");
			infoGrid.addComponent(titleLabel);

			infoGrid.addComponent(new Label("Author: "));
			Label authorLabel = new Label(golemM.getAuthor());
			authorLabel.setWidth("300px");
			infoGrid.addComponent(authorLabel);

			infoGrid.addComponent(new Label("Published: "));
			Label publishedLabel = new Label(golemM.getPublished() + "");
			publishedLabel.setWidth("300px");
			infoGrid.addComponent(publishedLabel);

			infoGrid.addComponent(new Label("Message: "));
			Label messageLabel = new Label(golemM.getMessage());
			messageLabel.setWidth("300px");
			infoGrid.addComponent(messageLabel);

			infoGrid.addComponent(new Label("URL: "));
			Label urlLabel = new Label(golemM.getURL());
			urlLabel.setWidth("300px");
			infoGrid.addComponent(urlLabel);
		}

		panel.setContent(infoGrid);
		return panel;
	}
}
