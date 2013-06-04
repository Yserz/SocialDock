/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.twitterview.component;

import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.web.ui.nyt.component.NYTDetailPanel;
import de.fhb.sd.web.ui.util.DetailPanel;
import de.fhb.sd.web.ui.util.MessageTable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwitterMessageTable extends MessageTable {

	private static final Logger LOG = Logger.getLogger(TwitterMessageTable.class.getName());
	private TwitterLocal twitter;

	public TwitterMessageTable(final TwitterLocal twitter) {
		super(new NYTDetailPanel(null));
		this.twitter = twitter;
//		twitter.start();
		addData();
	}

	@Override
	protected String[] addHeader() {
		return new String[]{"Author", "Message"};
	}

	@Override
	protected DetailPanel getNewDetailPanel() {
		return new NYTDetailPanel(selectedMessage);
	}

	@Override
	protected void addData() {
//		try {
		messageTable.removeAllItems();


		List<Message> allMessages = new ArrayList<>(twitter.getMessages());
		for (Message message : allMessages) {
			String[] messageAtt = new String[]{
				message.getAuthor(),
				message.getMessage()
			};

			messageTable.addItem(messageAtt, message);
		}
//		} catch (NullPointerException e) {
//			LOG.log(Level.INFO, "Nullpointer in TwitterMessageTable addData()!");
//		}

	}
}
