package de.fhb.sd.web;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import de.fhb.sd.api.golem.GolemLocal;
import de.fhb.sd.api.kernel.KernelServiceLocal;
import de.fhb.sd.api.nyt.NewYorkTimesLocal;
import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.web.ui.aboutview.AboutView;
import de.fhb.sd.web.ui.errorview.ErrorView;
import de.fhb.sd.web.ui.golem.GolemView;
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
import org.osgi.framework.InvalidSyntaxException;
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
	public final String GOLEM = "golem";
	public final String ABOUT = "about";
	private static TwitterLocal twitter;
	private static NewYorkTimesLocal nyt;
	private static GolemLocal golem;
	private Navigator nav = new Navigator(this, this);

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	@Override
	protected void init(final VaadinRequest request) {
		nav.setErrorView(new ErrorView("Service Unavailable"));
		nav.addView(HOME, WelcomeView.class);

		nav.addView(TWITTER, TwitterView.class);
		nav.addView(NYT, NewYorkTimesView.class);
		nav.addView(GOLEM, GolemView.class);
		nav.addView(ALL, MainView.class);

		nav.addView(ABOUT, AboutView.class);

		navTo(HOME);
	}

	public void navTo(String to) {
		nav.navigateTo(to);
	}

	@Override
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		bundleName = bundleContext.getBundle().getSymbolicName();

		context.addServiceListener(this);
		try {
			ServiceReference[] srl = context.getServiceReferences(TwitterLocal.class.getName(), null);
			for (int i = 0; srl != null && i < srl.length; i++) {
				this.serviceChanged(new ServiceEvent(ServiceEvent.REGISTERED, srl[i]));
			}
			srl = context.getServiceReferences(NewYorkTimesLocal.class.getName(), null);
			for (int i = 0; srl != null && i < srl.length; i++) {
				this.serviceChanged(new ServiceEvent(ServiceEvent.REGISTERED, srl[i]));
			}
			srl = context.getServiceReferences(GolemLocal.class.getName(), null);
			for (int i = 0; srl != null && i < srl.length; i++) {
				this.serviceChanged(new ServiceEvent(ServiceEvent.REGISTERED, srl[i]));
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
//		registerTwitterService();
//		registerNYTService();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		context.removeServiceListener(this);
	}

	@Override
	public void serviceChanged(ServiceEvent event) {

		String[] objectClass = (String[]) event.getServiceReference().getProperty("objectClass");

		if (objectClass[0].contains("de.fhb.sd")) {
			if (event.getType() == ServiceEvent.REGISTERED) {
				log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " registered.");
				if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.twitter.TwitterLocal")) {
					registerTwitterService();
				} else if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.nyt.NewYorkTimesLocal")) {
					registerNYTService();
				} else if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.golem.GolemLocal")) {
					registerGolemService();
				}
			} else if (event.getType() == ServiceEvent.UNREGISTERING) {
				log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " unregistered.");
				if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.twitter.TwitterLocal")) {
					twitter = null;
				} else if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.nyt.NewYorkTimesLocal")) {
					nyt = null;
				} else if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.golem.GolemLocal")) {
					golem = null;
				}
			} else if (event.getType() == ServiceEvent.MODIFIED) {
				log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " modified.");
				if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.twitter.TwitterLocal")) {
					registerTwitterService();
				} else if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.nyt.NewYorkTimesLocal")) {
					registerNYTService();
				} else if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.golem.GolemLocal")) {
					registerGolemService();
				}
			}
		}
	}

	private void log(String log) {
		LOG.log(Level.INFO, "{0}: {1}", new Object[]{bundleName, log});
	}

	private void registerTwitterService() {
		log("Registering TwitterService");
		try {
			twitter = (TwitterLocal) bundleContext.getService(bundleContext.getServiceReference(TwitterLocal.class.getName()));
			twitter.start();
		} catch (NullPointerException | ServiceUnavailableException e) {
			log("ServiceUnavailableException: Twitter-Bundle unavailable.");
		}
	}

	private void registerNYTService() {
		log("Registering NYTService");
		try {
			nyt = (NewYorkTimesLocal) bundleContext.getService(bundleContext.getServiceReference(NewYorkTimesLocal.class.getName()));
		} catch (NullPointerException | ServiceUnavailableException e) {
			log("ServiceUnavailableException: NYT-Bundle unavailable.");
		}
	}

	private void registerGolemService() {
		log("Registering GolemService");
		try {
			golem = (GolemLocal) bundleContext.getService(bundleContext.getServiceReference(GolemLocal.class.getName()));
		} catch (NullPointerException | ServiceUnavailableException e) {
			log("ServiceUnavailableException: Golem-Bundle unavailable.");
		}
	}

	public TwitterLocal getTwitter() {
		return twitter;
	}

	public NewYorkTimesLocal getNyt() {
		return nyt;
	}

	public GolemLocal getGolem() {
		return golem;
	}
}
