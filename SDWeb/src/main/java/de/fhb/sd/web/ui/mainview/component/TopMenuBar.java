/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.mainview.component;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

import de.fhb.sd.web.WebBundleService;

/**
 *
 * @author MacYser
 */
public class TopMenuBar extends MenuBar {

	private Command home;
	private Command all;
	private Command twitter;
	private Command nyt;
	private Command golem;
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

		golem = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				getMyUI().navTo(getMyUI().GOLEM);
			}
		};
		addItem("Golem", golem);

		about = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuBar.MenuItem selectedItem) {
				getMyUI().navTo(getMyUI().ABOUT);
			}
		};
		addItem("About", about);
	}

	private WebBundleService getMyUI() {
		return (WebBundleService) getUI();
	}
}
