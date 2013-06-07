/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.util;

import com.vaadin.data.Property;
import com.vaadin.ui.*;
import de.fhb.sd.domain.entity.Message;

import java.util.Map;

abstract public class MessageTable extends CustomComponent {

	protected Message selectedMessage;
	private DetailPanel detailPanel;
	protected Table messageTable;
	protected VerticalSplitPanel content = new VerticalSplitPanel();

	protected MessageTable(DetailPanel detailPanel) {
		super();
		messageTable = new Table();
		messageTable.setSizeFull();
		this.detailPanel = detailPanel;
		content.setSizeFull();
		content.setSplitPosition(70, Unit.PERCENTAGE);
		content.addComponent(messageTable);
		content.addComponent(detailPanel);
		setCompositionRoot(content);
		messageTable.markAsDirtyRecursive();

		init();
	}

	public void updateDetailPanel() {
		DetailPanel newDetail = getNewDetailPanel();
		content.replaceComponent(detailPanel, newDetail);
		detailPanel = newDetail;
	}

	private void init() {
		for (Map.Entry<String, Class> stringClassEntry : addHeader().entrySet()) {
			messageTable.addContainerProperty(stringClassEntry.getKey(), stringClassEntry.getValue(), null);
		}
		messageTable.setSelectable(true);
		messageTable.setImmediate(true);
		setHeight(100, Unit.PERCENTAGE);

		messageTable.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				Notification.show("Item clicked: " + event.getProperty().getValue());
				selectedMessage = (Message) event.getProperty().getValue();
				updateDetailPanel();
			}
		});
	}

	abstract protected void addData();

	abstract protected Map<String, Class> addHeader();

	abstract protected DetailPanel getNewDetailPanel();
}
