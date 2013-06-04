/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.nyt.component;

import de.fhb.sd.api.nyt.NewYorkTimesLocal;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.web.ui.util.MessageTable;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NYTMessageTable extends MessageTable {

	private static final Logger LOG = Logger.getLogger(NYTMessageTable.class.getName());
	private NewYorkTimesLocal nyt;

	public NYTMessageTable(final NewYorkTimesLocal nyt) {
		super();
		this.nyt = nyt;
		addData();
	}

	@Override
	protected String[] addHeader() {
		return new String[]{"Abstract"};
	}

	@Override
	protected void addData() {
		try {
			messageTable.removeAllItems();

			List<Message> messages = nyt.getMessages();
			for (Message message : messages) {
				String[] messageAtt = new String[]{
					message.getMessage()
				};

				messageTable.addItem(messageAtt, message);
			}
		} catch (NullPointerException e) {
			LOG.log(Level.INFO, "Nullpointer in NYTMessageTable addData()!");
		}
	}
}
