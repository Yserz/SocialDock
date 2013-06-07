/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.mainview.component;

import de.fhb.sd.api.nyt.NewYorkTimesLocal;
import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.domain.entity.NewYorkTimesMessage;
import de.fhb.sd.domain.entity.TwitterMessage;
import de.fhb.sd.web.ui.nyt.component.NYTDetailPanel;
import de.fhb.sd.web.ui.twitterview.component.TwitterDetailPanel;
import de.fhb.sd.web.ui.util.DetailPanel;
import de.fhb.sd.web.ui.util.MessageTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMessageTable extends MessageTable {

	private static final Logger LOG = Logger.getLogger(MainMessageTable.class.getName());
	private TwitterLocal twitter;
	private NewYorkTimesLocal nyt;

	public MainMessageTable(final TwitterLocal twitter, final NewYorkTimesLocal nyt) {
		super(new NYTDetailPanel(null));
		this.twitter = twitter;
		this.nyt = nyt;
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
		if (selectedMessage instanceof NewYorkTimesMessage) {
			return new NYTDetailPanel(selectedMessage);
		}
		if (selectedMessage instanceof TwitterMessage) {
			return new TwitterDetailPanel(selectedMessage);
		}
		return null;
	}

	@Override
	protected void addData() {
		try {
			messageTable.removeAllItems();
			List<Message> allMessages = new ArrayList<>();
			if (twitter != null) {
				allMessages.addAll(twitter.getMessages());
			}
			if (nyt != null) {
				allMessages.addAll(nyt.getMessages());
			}
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
