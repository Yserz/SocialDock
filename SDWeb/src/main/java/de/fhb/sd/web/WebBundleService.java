package de.fhb.sd.web;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import de.fhb.sd.api.kernel.KernelServiceLocal;
import de.fhb.sd.api.nyt.NewYorkTimesLocal;
import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.web.ui.aboutview.AboutView;
import de.fhb.sd.web.ui.errorview.ErrorView;
import de.fhb.sd.web.ui.mainview.MainView;
import de.fhb.sd.web.ui.nyt.NewYorkTimesView;
import de.fhb.sd.web.ui.twitterview.TwitterView;
import de.fhb.sd.web.ui.welcomeview.WelcomeView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.glassfish.osgicdi.OSGiService;
import org.glassfish.osgicdi.ServiceUnavailableException;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

@Title("SocialDock")
@PreserveOnRefresh
public class WebBundleService extends UI implements BundleActivator, ServiceListener {

	private final static Logger LOG = Logger.getLogger(WebBundleService.class.getName());
	private BundleContext bundleContext;
	private String bundleName;
	public final String HOME = "";
	public final String ALL = "all";
	public final String TWITTER = "twitter";
	public final String NYT = "nyt";
	public final String ABOUT = "about";
//	@Inject
//	@OSGiService(dynamic = true)
	public TwitterLocal twitter;
//	@Inject
//	@OSGiService(dynamic = true)
	public NewYorkTimesLocal nyt;
	@Inject
	@OSGiService(dynamic = false)
	private KernelServiceLocal kernel;
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
		nav.setErrorView(new ErrorView("Service Unavailable"));
//		homeView = new WelcomeView();
		nav.addView(HOME, WelcomeView.class);

		nav.addView(TWITTER, TwitterView.class);
		nav.addView(NYT, NewYorkTimesView.class);
		nav.addView(ALL, MainView.class);
//		registerTwitterService();
//		registerNYTService();

//		aboutView = new AboutView();
		nav.addView(ABOUT, AboutView.class);

		navTo(HOME);
	}

	public void navTo(String to) {
		log("Navigating to: " + to);
		nav.navigateTo(to);
	}

	@Override
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		bundleName = bundleContext.getBundle().getSymbolicName();

		context.addServiceListener(this);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		context.removeServiceListener(this);
	}

	@Override
	public void serviceChanged(ServiceEvent event) {

		String[] objectClass = (String[]) event.getServiceReference().getProperty("objectClass");

		if (event.getType() == ServiceEvent.REGISTERED) {
			log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " registered.");
			if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.twitter.TwitterLocal")) {
				registerTwitterService();
			} else if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.nyt.NewYorkTimesLocal")) {
				registerNYTService();
			}
		} else if (event.getType() == ServiceEvent.UNREGISTERING) {
			log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " unregistered.");
		} else if (event.getType() == ServiceEvent.MODIFIED) {
			log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " modified.");
			if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.twitter.TwitterLocal")) {
				registerTwitterService();
			} else if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.nyt.NewYorkTimesLocal")) {
				registerNYTService();
			}
		}
	}

	private void log(String log) {
		LOG.log(Level.INFO, "{0}: {1}", new Object[]{bundleName, log});
	}

	private void unregisterTwitterService() {
		try {
			twitter = null;
			bundleContext.ungetService(bundleContext.getServiceReference(TwitterLocal.class.getName()));
		} catch (NullPointerException | ServiceUnavailableException e) {
			log("ServiceUnavailableException: Twitter- or NYT-Bundle unavailable.");
		}
		nav.removeView(TWITTER);
		nav.removeView(ALL);
	}

	private void unregisterNYTService() {
		try {
			nyt = null;
			bundleContext.ungetService(bundleContext.getServiceReference(NewYorkTimesLocal.class.getName()));
		} catch (NullPointerException | ServiceUnavailableException e) {
			log("ServiceUnavailableException: Twitter- or NYT-Bundle unavailable.");
		}
		nav.removeView(NYT);
		nav.removeView(ALL);
	}

	private void registerTwitterService() {
//		try {
//		System.out.println("Location: " + bundleContext.getBundle().getLocation());
//
//		while (kernel.getBundle("SDTwitter").getState() != BundleEvent.STARTED) {
//			LOG.log(Level.INFO, "Bundle SDTwitter not started yet...");
//		}
		ServiceReference twitterRef = bundleContext.getServiceReference(TwitterLocal.class.getName());
		if (twitterRef == null) {
			log("TwitterRef == null");
		}
		twitter = (TwitterLocal) bundleContext.getService(twitterRef);
		if (twitter == null) {
			log("twitterService == null");
		}
//		} catch (NullPointerException | ServiceUnavailableException e) {
//			LOG.log(Level.INFO, "ServiceUnavailableException: Twitter-Bundle unavailable.");
//		}
//		nav.removeView(TWITTER);
//		twitterView = new TwitterView(twitter);
//		nav.addView(TWITTER, twitterView);
//		registerMainView();
	}

	private void registerNYTService() {
		try {
//			while (kernel.getBundle("SDNewYorkTimes").getState() != BundleEvent.STARTED) {
//			}
			nyt = (NewYorkTimesLocal) bundleContext.getService(bundleContext.getServiceReference(NewYorkTimesLocal.class.getName()));
		} catch (NullPointerException | ServiceUnavailableException e) {
			log("ServiceUnavailableException: NYT-Bundle unavailable.");
		}
//		nytView = new NewYorkTimesView(nyt);
//		nav.addView(NYT, nytView);
//		registerMainView();
	}
//	private void registerMainView() {
//		nav.removeView(ALL);
//		if (twitter != null && nyt != null) {
//			mainView = new MainView(twitter, nyt);
//			nav.addView(ALL, mainView);
//		} else {
//			log("ServiceUnavailableException: Twitter- or NYT-Bundle unavailable. Cant init MainView");
//		}
//	}
}
