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

import de.fhb.sd.api.nyt.NewYorkTimesLocal;
import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.web.ui.aboutview.AboutView;
import de.fhb.sd.web.ui.mainview.MainView;
import de.fhb.sd.web.ui.nyt.NewYorkTimesView;
import de.fhb.sd.web.ui.twitterview.TwitterView;
import de.fhb.sd.web.ui.welcomeview.WelcomeView;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import org.glassfish.osgicdi.OSGiService;
import org.glassfish.osgicdi.ServiceUnavailableException;

/**
 *
 * @author MacYser
 */
@Title("SocialDock")
@PreserveOnRefresh
public class SDWebUI extends UI {

	private static final Logger LOG = Logger.getLogger(SDWebUI.class.getName());
	public final String HOME = "";
	public final String ALL = "all";
	public final String TWITTER = "twitter";
	public final String NYT = "nyt";
	public final String ABOUT = "about";
	@Inject
	@OSGiService(dynamic = true)
	private TwitterLocal twitter;
	@Inject
	@OSGiService(dynamic = true)
	private NewYorkTimesLocal nyt;
	private Navigator nav = new Navigator(this, this);
	private MainView mainView;
	private TwitterView twitterView;
	private NewYorkTimesView nytView;
	private AboutView aboutView;
	private WelcomeView homeView;

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	@Override
	protected void init(final VaadinRequest request) {
		homeView = new WelcomeView();
		nav.addView(HOME, homeView);

		try {
			twitter.start();
		} catch (ServiceUnavailableException e) {
			LOG.log(Level.INFO, "ServiceUnavailableException: Twitter-Bundle unavailable.");
		}
		try {
			mainView = new MainView(twitter, nyt);
			nav.addView(ALL, mainView);
		} catch (ServiceUnavailableException e) {
			LOG.log(Level.INFO, "ServiceUnavailableException: Twitter- or NYT-Bundle unavailable.");
		}
		try {
			twitterView = new TwitterView(twitter);
			nav.addView(TWITTER, twitterView);
		} catch (ServiceUnavailableException e) {
			LOG.log(Level.INFO, "ServiceUnavailableException: Twitter-Bundle unavailable.");
		}
		try {
			nytView = new NewYorkTimesView(nyt);
			nav.addView(NYT, nytView);
		} catch (ServiceUnavailableException e) {
			LOG.log(Level.INFO, "ServiceUnavailableException: NYT-Bundle unavailable.");
		}

		aboutView = new AboutView();
		nav.addView(ABOUT, aboutView);

		navTo(HOME);
	}

	public void navTo(String to) {
		nav.navigateTo(to);
	}
}
