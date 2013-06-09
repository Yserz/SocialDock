/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.util;

import com.vaadin.ui.CustomComponent;
import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.web.ui.twitterview.component.TwitterMessageTable;

/**
 *
 * @author MacYser
 */
public class MessageTableWithDetails extends CustomComponent {

	private MessageTable table;

	public MessageTableWithDetails(MessageTable table) {
		super();
		this.table = table;
		setSizeFull();
		setCompositionRoot(table);
	}
}
