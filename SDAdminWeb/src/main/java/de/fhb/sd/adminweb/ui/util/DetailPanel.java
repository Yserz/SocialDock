package de.fhb.sd.adminweb.ui.util;

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
		vertical.addComponent(layoutContent);
	}

	protected abstract AbstractLayout addDetailContent();
}