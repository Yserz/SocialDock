/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb.ui.mainview.component;

import com.vaadin.ui.MenuBar;
import de.fhb.sd.adminweb.ui.SDAdminWebUI;

/**
 *
 * @author MacYser
 */
public class TopMenuBar extends MenuBar {

	private Command main;
	private Command allBundles;
	private Command about;

	public TopMenuBar() {
		super();
		init();
	}

	private void init() {
		setSizeFull();
		addItems();
	}

	private void addItems() {
		main = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuBar.MenuItem selectedItem) {
				getMyUI().navTo(getMyUI().MAIN);
			}
		};
		addItem("MyBundles", main);

		allBundles = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuBar.MenuItem selectedItem) {
				getMyUI().navTo(getMyUI().ALL);
			}
		};
		addItem("AllBundles", allBundles);

		about = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuBar.MenuItem selectedItem) {
				getMyUI().navTo(getMyUI().ABOUT);
			}
		};
		addItem("About", about);
	}

	private SDAdminWebUI getMyUI() {
		return (SDAdminWebUI) getUI();
	}
}
