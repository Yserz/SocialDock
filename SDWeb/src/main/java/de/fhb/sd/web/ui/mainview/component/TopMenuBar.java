/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.mainview.component;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

import de.fhb.sd.web.SDWebUI;

/**
 *
 * @author MacYser
 */
public class TopMenuBar extends MenuBar {

	private Command home;
	private Command all;
	private Command twitter;
	private Command nyt;
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
		home = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuBar.MenuItem selectedItem) {
				getMyUI().navTo(getMyUI().HOME);
			}
		};
		addItem("Home", home);

		all = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuBar.MenuItem selectedItem) {
				getMyUI().navTo(getMyUI().ALL);
			}
		};
		addItem("SocialDock", all);

		twitter = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuBar.MenuItem selectedItem) {
				getMyUI().navTo(getMyUI().TWITTER);
			}
		};
		addItem("Twitter", twitter);

		nyt = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				getMyUI().navTo(getMyUI().NYT);
			}
		};
		addItem("NewYorkTimes", nyt);

		about = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuBar.MenuItem selectedItem) {
				getMyUI().navTo(getMyUI().ABOUT);
			}
		};
		addItem("About", about);
	}

	private SDWebUI getMyUI() {
		return (SDWebUI) getUI();
	}
}
