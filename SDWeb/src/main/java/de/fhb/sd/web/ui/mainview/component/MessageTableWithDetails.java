/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.mainview.component;

import com.vaadin.ui.CustomComponent;
import de.fhb.sd.api.twitter.TwitterLocal;

/**
 *
 * @author MacYser
 */
public class MessageTableWithDetails extends CustomComponent {

	private MainMessageTable table;

	public MessageTableWithDetails(final TwitterLocal twitter, final de.fhb.sd.api.nyt.NewYorkTimesLocal nyt) {
		super();
		table = new MainMessageTable(twitter, nyt);
		new Thread(table).start();
		setSizeFull();
		setCompositionRoot(table);
	}
}
