/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.twitterview.component;

import com.vaadin.data.Item;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.domain.entity.TwitterMessage;
import de.fhb.sd.web.ui.util.DetailPanel;
import de.fhb.sd.web.ui.util.MessageTable;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwitterMessageTable extends MessageTable {

	private static final Logger LOG = Logger.getLogger(TwitterMessageTable.class.getName());
	private TwitterLocal twitter;
	private final String TABLEHEADER_AUTHOR = "Author";
	private final String TABLEHEADER_IMAGE = "Image";
	private final String TABLEHEADER_MESSAGE = "Message";

	public TwitterMessageTable(final TwitterLocal twitter) {
		super(new TwitterDetailPanel(null));
		this.twitter = twitter;
		addData();
	}

	@Override
	protected List<ColumnHeader> addColumnHeader() {
		List<ColumnHeader> columnList = new ArrayList<>();
		columnList.add(new ColumnHeader(TABLEHEADER_IMAGE, Image.class));
		columnList.add(new ColumnHeader(TABLEHEADER_AUTHOR, String.class));
		columnList.add(new ColumnHeader(TABLEHEADER_MESSAGE, String.class));
		return columnList;
	}

	@Override
	protected DetailPanel getNewDetailPanel() {
		return new TwitterDetailPanel(selectedMessage);
	}

	@Override
	protected void addData() {
		try {
			messageTable.removeAllItems();
			List<Message> allMessages = new ArrayList<>(twitter.getMessages());
			for (Message message : allMessages) {
				TwitterMessage twitterM = (TwitterMessage) message;
				ExternalResource resource = new ExternalResource(twitterM.getProfileImageUrlOfUser());
				Image img = new Image();
				img.setSource(resource);
				img.setWidth(45, Unit.PIXELS);
				img.setHeight(45, Unit.PIXELS);

				Item item = messageTable.addItem(message);
				item.getItemProperty(TABLEHEADER_IMAGE).setValue(img);
				item.getItemProperty(TABLEHEADER_AUTHOR).setValue(twitterM.getAuthor());
				item.getItemProperty(TABLEHEADER_MESSAGE).setValue(twitterM.getMessage());
			}
		} catch (NullPointerException e) {
			LOG.log(Level.INFO, "Nullpointer in TwitterMessageTable addData()!");
		}

	}
}
