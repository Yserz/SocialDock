/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.twitterview.component;

import com.vaadin.ui.Image;
import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.web.ui.util.DetailPanel;
import de.fhb.sd.web.ui.util.MessageTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwitterMessageTable extends MessageTable {

	private static final Logger LOG = Logger.getLogger(TwitterMessageTable.class.getName());
	private TwitterLocal twitter;

	public TwitterMessageTable(final TwitterLocal twitter) {
		super(new TwitterDetailPanel(null));
		this.twitter = twitter;
		addData();
	}

	@Override
	protected Map<String, Class> addHeader() {
		Map<String, Class> map = new HashMap();
		map.put("Author", String.class);
		map.put("Message", String.class);
		return map;
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
				String[] messageAtt = new String[]{
					message.getAuthor(),
					message.getMessage()
				};

				messageTable.addItem(messageAtt, message);
			}
		} catch (NullPointerException e) {
			LOG.log(Level.INFO, "Nullpointer in TwitterMessageTable addData()!");
		}

	}
}
