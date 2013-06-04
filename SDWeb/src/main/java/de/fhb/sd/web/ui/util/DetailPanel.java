package de.fhb.sd.web.ui.util;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import de.fhb.sd.domain.entity.Message;

abstract public class DetailPanel extends CustomComponent {

	protected VerticalLayout vertical = new VerticalLayout();
	protected Message selectedMessage;

	public DetailPanel(Message selectedMessage) {
		super();
		this.selectedMessage = selectedMessage;
		setCompositionRoot(vertical);
		vertical.setMargin(true);
		setSizeFull();

		init();
	}

	private void init() {

		AbstractLayout layoutContent = addDetailContent();
//		GridLayout infoGrid = new GridLayout(2, 2);
//		infoGrid.setMargin(true);
//		infoGrid.addComponent(new Label("Author: "));
//		infoGrid.addComponent(new Label(selectedMessage != null ? selectedMessage.getAuthor() : ""));
//
//		infoGrid.addComponent(new Label("Message: "));
//		infoGrid.addComponent(new Label(selectedMessage != null ? selectedMessage.getMessage() : ""));

		vertical.addComponent(layoutContent);
	}

	protected abstract AbstractLayout addDetailContent();
}