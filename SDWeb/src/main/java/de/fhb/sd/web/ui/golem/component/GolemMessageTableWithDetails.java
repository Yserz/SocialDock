/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.golem.component;

import com.vaadin.ui.CustomComponent;
import de.fhb.sd.api.golem.GolemLocal;
import de.fhb.sd.api.nyt.NewYorkTimesLocal;

/**
 * @author MacYser
 */
public class GolemMessageTableWithDetails extends CustomComponent {

	private GolemMessageTable table;

	public GolemMessageTableWithDetails(final GolemLocal golemLocal) {
		super();
		table = new GolemMessageTable(golemLocal);
		setSizeFull();
		setCompositionRoot(table);
	}
}
