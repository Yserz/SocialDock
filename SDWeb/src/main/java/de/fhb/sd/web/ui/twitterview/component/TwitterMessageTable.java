/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.twitterview.component;

import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.web.ui.util.MessageTable;

import java.util.ArrayList;
import java.util.List;

public class TwitterMessageTable extends MessageTable {

	private TwitterLocal twitter;

	public TwitterMessageTable(final TwitterLocal twitter) {
		super();
		this.twitter = twitter;
		twitter.start();
		addData();
	}

	@Override
	protected String[] addHeader() {
		return new String[]{"Author", "Message"};
	}

	@Override
	protected void addData() {
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
}
