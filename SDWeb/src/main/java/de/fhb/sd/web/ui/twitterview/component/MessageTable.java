/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.twitterview.component;

import com.vaadin.data.Property;
import com.vaadin.server.Sizeable;
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
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;
import org.glassfish.osgicdi.OSGiService;

public class MessageTable extends CustomComponent implements Runnable {

	private TwitterLocal twitter;
	private final String[] fieldNames = new String[]{"Author", "Message"};
	private Message selectedMessage;
	private DetailPanel detail;
	private Table messageTable;
	private VerticalSplitPanel content = new VerticalSplitPanel();

	public MessageTable(final TwitterLocal twitter) {
		super();
		this.twitter = twitter;
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
//		messageTable.refreshRenderedCells();
	}

	public void updateDetailPanel() {
		DetailPanel newDetail = new DetailPanel();
		content.replaceComponent(detail, newDetail);
		detail = newDetail;
	}

	private void init() {
		addHeader();
//			twitter.start();
		addData();

		messageTable.setSelectable(true);
		messageTable.setImmediate(true);
		setHeight(100, Sizeable.Unit.PERCENTAGE);

		messageTable.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				Notification.show("Item clicked: " + event.getProperty().getValue());
				selectedMessage = (Message) event.getProperty().getValue();
				updateDetailPanel();
			}
		});
		twitter.start();
	}

	private void addHeader() {
		for (String field : fieldNames) {
			messageTable.addContainerProperty(field, String.class, "");
		}
	}

	private void addData() {
		messageTable.removeAllItems();

		List<Message> allMessages = new ArrayList<>(twitter.getMessages());
		for (Message message : allMessages) {
			String[] messageAtt = new String[]{
				message.getAuthor(),
				message.getMessage()
			};

			messageTable.addItem(messageAtt, message);
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
