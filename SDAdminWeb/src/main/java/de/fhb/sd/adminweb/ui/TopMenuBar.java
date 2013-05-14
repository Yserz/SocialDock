/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb.ui;

import com.vaadin.ui.MenuBar;

/**
 *
 * @author MacYser
 */
public class TopMenuBar extends MenuBar {

	private Command test;

	public TopMenuBar() {
		super();
		init();
	}

	private void init() {
		setSizeFull();


		addItems();
	}

	private void addItems() {
		test = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuBar.MenuItem selectedItem) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		};
		addItem("test", test);
	}
}
