/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb.ui.mainview.component;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.MenuBar;
import de.fhb.sd.adminweb.ui.SDAdminWebUI;

/**
 *
 * @author MacYser
 */
public class TopMenuBar extends MenuBar {

	private Command test;
	private SDAdminWebUI ui;

	public TopMenuBar() {
		super();
		this.ui = (SDAdminWebUI) getUI();
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
				ui.navTo(ui.MAIN);
			}
		};
		addItem("test", test);
	}
}
