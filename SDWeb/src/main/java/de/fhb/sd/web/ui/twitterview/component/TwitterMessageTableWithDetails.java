/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.twitterview.component;

import com.vaadin.ui.CustomComponent;
import de.fhb.sd.api.twitter.TwitterLocal;
import javax.inject.Inject;
import javax.inject.Named;
import org.glassfish.osgicdi.OSGiService;

/**
 *
 * @author MacYser
 */
public class TwitterMessageTableWithDetails extends CustomComponent {

	private MessageTable table;

	public TwitterMessageTableWithDetails(final TwitterLocal twitter) {
		super();
		table = new MessageTable(twitter);
		new Thread(table).start();
		setSizeFull();
		setCompositionRoot(table);
	}
}
