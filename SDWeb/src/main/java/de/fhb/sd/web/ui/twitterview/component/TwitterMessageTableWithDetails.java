/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.twitterview.component;

import com.vaadin.data.Property;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.domain.entity.Message;
import org.osgi.framework.Bundle;

/**
 *
 * @author MacYser
 */
public class TwitterMessageTableWithDetails extends CustomComponent {

	private TwitterLocal twitter;
	private Message selectedMessage;
	private DetailPanel detail;
	private MessageTable table;
	private VerticalSplitPanel content = new VerticalSplitPanel();

	public TwitterMessageTableWithDetails(final TwitterLocal twitter) {
		super();
		this.twitter = twitter;


		content.setSizeFull();
		content.setSplitPosition(70, Unit.PERCENTAGE);

		detail = new DetailPanel();
		table = new MessageTable(twitter);
		table.markAsDirtyRecursive();
		new Thread(table).start();
		content.addComponent(table);

		content.addComponent(detail);
		setCompositionRoot(content);
		setSizeFull();

	}

	public void updateDetailPanel() {
		DetailPanel newDetail = new DetailPanel();
		content.replaceComponent(detail, newDetail);
		detail = newDetail;
	}

	private class MessageTable extends Table implements Runnable {

		private final String[] fieldNames = new String[]{"Author", "Message"};

		public MessageTable(final TwitterLocal twitter) {
			super();
			init();
		}

		public void updateTable() {
			addData();
			refreshRowCache();
			refreshRenderedCells();
		}

		private void init() {
			addHeader();
			addData();

			setSelectable(true);
			setImmediate(true);
			setHeight(100, Unit.PERCENTAGE);

			addValueChangeListener(new ValueChangeListener() {
				@Override
				public void valueChange(Property.ValueChangeEvent event) {
					Notification.show("Item clicked: " + event.getProperty().getValue());
					selectedMessage = (Message) event.getProperty().getValue();
					updateDetailPanel();
				}
			});
		}

		private void addHeader() {
			for (String field : fieldNames) {
				addContainerProperty(field, String.class, "");
			}
		}

		private void addData() {
			removeAllItems();
			for (Message message : twitter.getMessages()) {
				String[] messageAtt = new String[]{
					message.getAuthor(),
					message.getMessage()
				};

				addItem(messageAtt, message);
			}
		}

		@Override
		public void run() {
			boolean loop = true;

			while (loop) {
				try {
					Thread.sleep(10000);
					//update the data in table
					updateTable();

					refreshRowCache();
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
