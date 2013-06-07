/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.util;

import com.vaadin.data.Property;
import com.vaadin.ui.*;
import de.fhb.sd.domain.entity.Message;

import java.util.List;

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
		for (ColumnHeader column : addColumnHeader()) {
			messageTable.addContainerProperty(column.columnName,column.columnClass, column.defaultValue);
			messageTable.setColumnWidth(column, -1);
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

	abstract protected List<ColumnHeader> addColumnHeader();

	abstract protected DetailPanel getNewDetailPanel();

	protected class ColumnHeader{
		protected String columnName;
		protected Class columnClass;
		protected Object defaultValue;

		public ColumnHeader(String columnName, Class columnClass) {
			this.columnName = columnName;
			this.columnClass = columnClass;
		}

		public ColumnHeader(String columnName, Class columnClass, Object defaultValue) {
			this.columnName = columnName;
			this.columnClass = columnClass;
			this.defaultValue = defaultValue;
		}
	}
}
