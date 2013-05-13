/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 *
 * @author MacYser
 */
public class Jee6UIProvider extends UIProvider {

	@Override
	public UI createInstance(UICreateEvent event) {
		return ((Jee6VaadinServlet) VaadinServlet.getCurrent()).getUI();
	}

	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		return ((Jee6VaadinServlet) VaadinServlet.getCurrent()).getUI().getClass();
	}
}
