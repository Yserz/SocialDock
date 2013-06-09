/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.golem.component;

import com.vaadin.data.Item;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import de.fhb.sd.api.golem.GolemLocal;
import de.fhb.sd.domain.entity.GolemMessage;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.domain.entity.NewYorkTimesMessage;
import de.fhb.sd.web.ui.util.DetailPanel;
import de.fhb.sd.web.ui.util.MessageTable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GolemMessageTable extends MessageTable {

	private static final Logger LOG = Logger.getLogger(GolemMessageTable.class.getName());
	private final String TABLEHEADER_IMAGE = "Image";
	private final String TABLEHEADER_TITLE = "Title";
	private final String TABLEHEADER_AUTHOR = "Author";
	private final String TABLEHEADER_PUBLISHED = "Published";
	private GolemLocal golem;

	public GolemMessageTable(final GolemLocal golemLocal) {
		super(new GolemDetailPanel(null));
		this.golem = golemLocal;
		addData();
	}

	@Override
	protected List<ColumnHeader> addColumnHeader() {
		List<ColumnHeader> list = new ArrayList<>();
		list.add(new ColumnHeader(TABLEHEADER_IMAGE, Image.class));
		list.add(new ColumnHeader(TABLEHEADER_TITLE, String.class));
		list.add(new ColumnHeader(TABLEHEADER_AUTHOR, String.class));
		list.add(new ColumnHeader(TABLEHEADER_PUBLISHED, String.class));
		return list;
	}

	@Override
	protected DetailPanel getNewDetailPanel() {
		return new GolemDetailPanel(selectedMessage);
	}

	@Override
	protected void addData() {
		try {
			messageTable.removeAllItems();

			List<Message> messages = golem.getMessages();
			for (Message message : messages) {
				GolemMessage golemM = (GolemMessage) message;
				ExternalResource resource = new ExternalResource(golemM.getImageURLSmall());
				Image img = new Image();
				img.setSource(resource);
				img.setWidth(45, Unit.PIXELS);
				img.setHeight(45, Unit.PIXELS);

				Item item = messageTable.addItem(message);
				item.getItemProperty(TABLEHEADER_IMAGE).setValue(img);
				item.getItemProperty(TABLEHEADER_TITLE).setValue(golemM.getTitle());
				item.getItemProperty(TABLEHEADER_AUTHOR).setValue(golemM.getAuthor());
				item.getItemProperty(TABLEHEADER_PUBLISHED).setValue(golemM.getPublished() + "");
			}
		} catch (NullPointerException e) {
			LOG.log(Level.INFO, "Nullpointer in GolemMessageTable addData()!");
		}
	}
}
