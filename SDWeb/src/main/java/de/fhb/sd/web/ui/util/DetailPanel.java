package de.fhb.sd.web.ui.util;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import de.fhb.sd.domain.entity.Message;

abstract public class DetailPanel extends CustomComponent {

	protected Message selectedMessage;
	protected Panel detailPanel;

	public DetailPanel(Message selectedMessage) {
		super();
		this.selectedMessage = selectedMessage;
		detailPanel = new Panel();
		detailPanel.setHeight(300, Unit.PIXELS);
		setCompositionRoot(detailPanel);
		setSizeFull();

		init();
	}

	private void init() {
		detailPanel.setContent(addDetailContent());
	}

	protected abstract Panel addDetailContent();
}
