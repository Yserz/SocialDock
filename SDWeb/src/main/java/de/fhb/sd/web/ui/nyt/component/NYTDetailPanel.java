package de.fhb.sd.web.ui.nyt.component;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.nyt.domain.NewYorkTimesMessage;
import de.fhb.sd.web.ui.util.DetailPanel;

public class NYTDetailPanel extends DetailPanel {

	public NYTDetailPanel(Message selectedMessage) {
		super(selectedMessage);
	}

	@Override
	protected AbstractLayout addDetailContent() {
//		Title", "Author", "Abstract", "Section", "Published
		NewYorkTimesMessage nytM = (NewYorkTimesMessage) selectedMessage;
		GridLayout infoGrid = new GridLayout(2, 2);
		infoGrid.setMargin(true);
		infoGrid.addComponent(new Label("Title: "));
		infoGrid.addComponent(new Label(nytM != null ? nytM.getTitle() : ""));

		infoGrid.addComponent(new Label("Author: "));
		infoGrid.addComponent(new Label(nytM != null ? nytM.getAuthor() : ""));

		infoGrid.addComponent(new Label("Abstract: "));
		infoGrid.addComponent(new Label(nytM != null ? nytM.getMessage() : ""));

		infoGrid.addComponent(new Label("Section: "));
		infoGrid.addComponent(new Label(nytM != null ? nytM.getSection() : ""));

		infoGrid.addComponent(new Label("Published: "));
		infoGrid.addComponent(new Label(nytM != null ? nytM.getPublished().toString() : ""));

		return infoGrid;
	}
}
