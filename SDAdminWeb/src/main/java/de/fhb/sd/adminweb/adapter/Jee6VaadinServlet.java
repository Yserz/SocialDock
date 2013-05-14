/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb.adapter;

import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import javax.inject.Inject;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author MacYser
 */
@WebServlet(urlPatterns = "/*",
		initParams = {
	@WebInitParam(name = "UIProvider", value = "de.fhb.sd.adminweb.adapter.Jee6UIProvider")})
public class Jee6VaadinServlet extends VaadinServlet {

	@Inject
	private UI ui;

	public UI getUI() {
		return ui;
	}
}
