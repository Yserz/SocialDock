/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb.ui.mainview.component;

import com.vaadin.ui.Tree;

/**
 *
 * @author MacYser
 */
public class AdminTree extends Tree {

	public AdminTree() {
		super();
		init();
	}

	private void init() {
		addItem("Bla");
		setHeight(100, Unit.PERCENTAGE);
	}
}
