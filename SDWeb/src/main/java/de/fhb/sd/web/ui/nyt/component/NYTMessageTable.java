/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.nyt.component;

import com.vaadin.data.Item;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import de.fhb.sd.api.nyt.NewYorkTimesLocal;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.domain.entity.NewYorkTimesMessage;
import de.fhb.sd.web.ui.util.DetailPanel;
import de.fhb.sd.web.ui.util.MessageTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NYTMessageTable extends MessageTable {

	private static final Logger LOG = Logger.getLogger(NYTMessageTable.class.getName());
	private NewYorkTimesLocal nyt;
	private final String TABLEHEADER_IMAGE = "Image";
	private final String TABLEHEADER_TITLE= "Title";
	private final String TABLEHEADER_AUTHOR = "Author";
	private final String TABLEHEADER_SECTION = "Section";
	private final String TABLEHEADER_PUBLISHED = "Published";

	public NYTMessageTable(final NewYorkTimesLocal nyt) {
		super(new NYTDetailPanel(null));
		this.nyt = nyt;
		addData();
	}

	@Override
	protected Map<String, Class> addHeader() {
		Map<String, Class> map = new HashMap();
		map.put(TABLEHEADER_IMAGE, Image.class);
		map.put(TABLEHEADER_TITLE, String.class);
		map.put(TABLEHEADER_AUTHOR, String.class);
		map.put(TABLEHEADER_SECTION, String.class);
		map.put(TABLEHEADER_PUBLISHED, String.class);
		return map;
	}

	@Override
	protected DetailPanel getNewDetailPanel() {
		return new NYTDetailPanel(selectedMessage);
	}

	@Override
	protected void addData() {
		try {
			messageTable.removeAllItems();

			List<Message> messages = nyt.getMessages();
			for (Message message : messages) {
				NewYorkTimesMessage nytM = (NewYorkTimesMessage) message;
				ExternalResource resource = new ExternalResource(nytM.getMediaURL());
				Image img = new Image();
				img.setSource(resource);
				img.setWidth(45, Unit.PIXELS);
				img.setHeight(45, Unit.PIXELS);

				Item item = messageTable.addItem(message);
				item.getItemProperty(TABLEHEADER_IMAGE).setValue(img);
				item.getItemProperty(TABLEHEADER_TITLE).setValue(nytM.getTitle());
				item.getItemProperty(TABLEHEADER_AUTHOR).setValue(nytM.getAuthor());
				item.getItemProperty(TABLEHEADER_SECTION).setValue(nytM.getSection());
				item.getItemProperty(TABLEHEADER_PUBLISHED).setValue(nytM.getPublished()+"");

			}
		} catch (NullPointerException e) {
			LOG.log(Level.INFO, "Nullpointer in NYTMessageTable addData()!");
		}
	}
}
