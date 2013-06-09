package de.fhb.sd.web.ui.golem.component;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import de.fhb.sd.domain.entity.GolemMessage;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.domain.entity.NewYorkTimesMessage;
import de.fhb.sd.web.ui.util.DetailPanel;

public class GolemDetailPanel extends DetailPanel {

	public GolemDetailPanel(Message selectedMessage) {
		super(selectedMessage);
	}

	@Override
	protected AbstractLayout addDetailContent() {

		GridLayout infoGrid = new GridLayout(2, 5);

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
			infoGrid.addComponent(new Label(golemM.getTitle()));

			infoGrid.addComponent(new Label("Author: "));
			infoGrid.addComponent(new Label(golemM.getAuthor()));

			infoGrid.addComponent(new Label("Published: "));
			infoGrid.addComponent(new Label(golemM.getPublished() + ""));

			infoGrid.addComponent(new Label("Abstract: "));
			infoGrid.addComponent(new Label(golemM.getMessage()));

			infoGrid.addComponent(new Label("URL: "));
			infoGrid.addComponent(new Label(golemM.getURL()));
		}
		return infoGrid;
	}
}
