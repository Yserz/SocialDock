package de.fhb.sd.web.ui.nyt.component;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.domain.entity.NewYorkTimesMessage;
import de.fhb.sd.web.ui.util.DetailPanel;

public class NYTDetailPanel extends DetailPanel {

	public NYTDetailPanel(Message selectedMessage) {
		super(selectedMessage);
	}

	@Override
	protected AbstractLayout addDetailContent() {

		GridLayout infoGrid = new GridLayout(2, 5);

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
			infoGrid.addComponent(new Label(nytM.getTitle()));

			infoGrid.addComponent(new Label("Author: "));
			infoGrid.addComponent(new Label(nytM.getAuthor()));

			infoGrid.addComponent(new Label("Section: "));
			infoGrid.addComponent(new Label(nytM.getSection()));

			infoGrid.addComponent(new Label("Published: "));
			infoGrid.addComponent(new Label(nytM.getPublished() + ""));

			infoGrid.addComponent(new Label("Abstract: "));
			infoGrid.addComponent(new Label(nytM.getMessage()));
		}
		return infoGrid;
	}
}
