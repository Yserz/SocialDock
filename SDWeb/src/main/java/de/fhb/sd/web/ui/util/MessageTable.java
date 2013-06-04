/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.util;

import com.vaadin.data.Property;
import com.vaadin.ui.*;
import de.fhb.sd.domain.entity.Message;

abstract public class MessageTable extends CustomComponent implements Runnable {

	protected Message selectedMessage;
	protected DetailPanel detail;
	protected Table messageTable;
	protected VerticalSplitPanel content = new VerticalSplitPanel();

	protected MessageTable() {
		super();
		messageTable = new Table();
		detail = new DetailPanel();
		content.setSizeFull();
		content.setSplitPosition(70, Unit.PERCENTAGE);
		content.addComponent(messageTable);
		content.addComponent(detail);
		setCompositionRoot(content);
		messageTable.markAsDirtyRecursive();

		init();
	}


	public void updateTable() {
		addData();
		messageTable.refreshRowCache();
	}

	public void updateDetailPanel() {
		DetailPanel newDetail = new DetailPanel();
		content.replaceComponent(detail, newDetail);
		detail = newDetail;
	}

	private void init() {
		for (String field : addHeader()){
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

	@Override
	public void run() {
		boolean loop = true;

		while (loop) {
			try {
				Thread.sleep(10000);
				//update the data in table
				updateTable();

				messageTable.refreshRowCache();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loop = false;
			} catch (IllegalStateException ise) {
				ise.printStackTrace();
				loop = false;
			}
		}

	}

	private class DetailPanel extends CustomComponent {

		private VerticalLayout vertical = new VerticalLayout();
		private HorizontalLayout buttonLine = new HorizontalLayout();

		public DetailPanel() {
			super();
			setCompositionRoot(vertical);
			vertical.setMargin(true);
			setSizeFull();

			init();
		}

		private void init() {

			vertical.addComponent(buttonLine);

			GridLayout infoGrid = new GridLayout(2, 2);
			infoGrid.setMargin(true);
			infoGrid.addComponent(new Label("Author: "));
			infoGrid.addComponent(new Label(selectedMessage != null ? selectedMessage.getAuthor() : ""));

			infoGrid.addComponent(new Label("Message: "));
			infoGrid.addComponent(new Label(selectedMessage != null ? selectedMessage.getMessage() : ""));

			vertical.addComponent(infoGrid);
		}
	}
}
