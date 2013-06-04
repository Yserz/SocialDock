/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.twitterview.component;

import com.vaadin.ui.CustomComponent;
import de.fhb.sd.api.twitter.TwitterLocal;

/**
 *
 * @author MacYser
 */
public class TwitterMessageTableWithDetails extends CustomComponent {

	private TwitterMessageTable table;

	public TwitterMessageTableWithDetails(final TwitterLocal twitter) {
		super();
		table = new TwitterMessageTable(twitter);
		setSizeFull();
		setCompositionRoot(table);
	}
}
