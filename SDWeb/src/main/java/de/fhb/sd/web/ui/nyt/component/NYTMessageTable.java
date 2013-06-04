/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.nyt.component;

import de.fhb.sd.api.nyt.NewYorkTimesLocal;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.nyt.domain.NewYorkTimesMessage;
import de.fhb.sd.web.ui.util.MessageTable;

import java.util.List;

public class NYTMessageTable extends MessageTable {

	private NewYorkTimesLocal nyt;

	public NYTMessageTable(final NewYorkTimesLocal nyt) {
		super();
		this.nyt = nyt;
		addData();
	}

	@Override
	protected String[] addHeader() {
		return new String[]{"Title", "Author", "Abstract", "Section", "Published"};
	}

	@Override
	protected void addData() {
		messageTable.removeAllItems();

		List<Message> messages = nyt.getMessages();
		for (Message message : messages) {
			NewYorkTimesMessage nytM = (NewYorkTimesMessage) message;
			String[] messageAtt = new String[]{
					nytM.getTitle(),
					nytM.getAuthor(),
					nytM.getMessage(),
					nytM.getSection(),
					nytM.getPublished().toString()
			};

			messageTable.addItem(messageAtt, message);
		}
	}
}
