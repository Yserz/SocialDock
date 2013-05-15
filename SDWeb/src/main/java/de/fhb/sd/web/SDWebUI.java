/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.web.ui.aboutview.AboutView;
import de.fhb.sd.web.ui.mainview.MainView;
import javax.inject.Inject;
import org.glassfish.osgicdi.OSGiService;

/**
 *
 * @author MacYser
 */
@Title("SocialDock")
@PreserveOnRefresh
public class SDWebUI extends UI {

	public final String MAIN = "";
	public final String ABOUT = "about";
	@Inject
	@OSGiService(dynamic = false)
	private TwitterLocal twitter;
	private Navigator nav = new Navigator(this, this);
	private MainView mainView;
	private AboutView aboutView;

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	@Override
	protected void init(final VaadinRequest request) {
		mainView = new MainView(twitter);
		nav.addView(MAIN, mainView);

		aboutView = new AboutView();
		nav.addView(ABOUT, aboutView);

		navTo(MAIN);
	}

	public void navTo(String to) {
		nav.navigateTo(to);
	}
}
