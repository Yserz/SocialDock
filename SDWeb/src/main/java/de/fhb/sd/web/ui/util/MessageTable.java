/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.util;

import com.vaadin.data.Property;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.domain.entity.Message;

abstract public class MessageTable extends CustomComponent {

	protected Message selectedMessage;
	private DetailPanel detailPanel;
	protected Table messageTable;
	protected VerticalSplitPanel content = new VerticalSplitPanel();

	protected MessageTable(DetailPanel detailPanel) {
		super();
		messageTable = new Table();
		this.detailPanel = detailPanel;
		content.setSizeFull();
		content.setSplitPosition(70, Unit.PERCENTAGE);
		content.addComponent(messageTable);
		content.addComponent(detailPanel);
		setCompositionRoot(content);
		messageTable.markAsDirtyRecursive();

		init();
	}

//	public void updateTable() {
//		addData();
//		messageTable.refreshRowCache();
//	}
	public void updateDetailPanel() {
		DetailPanel newDetail = getNewDetailPanel();
		content.replaceComponent(detailPanel, newDetail);
		detailPanel = newDetail;
	}

	private void init() {
		for (String field : addHeader()) {
			messageTable.addContainerProperty(field, String.class, "");
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

	abstract protected String[] addHeader();

	abstract protected DetailPanel getNewDetailPanel();
//	@Override
//	public void run() {
//		boolean loop = true;
//
//		while (loop) {
//			try {
//				Thread.sleep(10000);
//				//update the data in table
//				updateTable();
//
//				messageTable.refreshRowCache();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				loop = false;
//			} catch (IllegalStateException ise) {
//				ise.printStackTrace();
//				loop = false;
//			}
//		}
//
//	}
}
