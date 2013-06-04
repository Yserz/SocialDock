/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.nyt.component;

import com.vaadin.ui.CustomComponent;
import de.fhb.sd.api.nyt.NewYorkTimesLocal;

/**
 * @author MacYser
 */
public class NYTMessageTableWithDetails extends CustomComponent {

	private NYTMessageTable table;

	public NYTMessageTableWithDetails(final NewYorkTimesLocal newYorkTimesLocal) {
		super();
		table = new NYTMessageTable(newYorkTimesLocal);
//		new Thread(table).start();
		setSizeFull();
		setCompositionRoot(table);
	}
}
