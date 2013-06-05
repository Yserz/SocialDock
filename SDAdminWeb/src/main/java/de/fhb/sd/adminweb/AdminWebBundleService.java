package de.fhb.sd.adminweb;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import de.fhb.sd.adminweb.ui.aboutview.AboutView;
import de.fhb.sd.adminweb.ui.allbundles.AllBundlesView;
import de.fhb.sd.adminweb.ui.mainview.MainView;
import de.fhb.sd.api.kernel.KernelServiceLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.osgicdi.ServiceUnavailableException;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

@Title("SocialDockAdmin")
@PreserveOnRefresh
public class AdminWebBundleService extends UI implements BundleActivator, ServiceListener {

	private final static Logger LOG = Logger.getLogger(AdminWebBundleService.class.getName());
	public final String MAIN = "";
	public final String ABOUT = "about";
	public final String ALL = "all";
	private static KernelServiceLocal kernel;
	private Navigator nav = new Navigator(this, this);
	private BundleContext bundleContext;
	private String bundleName;

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	@Override
	protected void init(final VaadinRequest request) {
		nav.addView(MAIN, MainView.class);

		nav.addView(ALL, AllBundlesView.class);
		nav.addView(ABOUT, AboutView.class);

		navTo(MAIN);
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
			ServiceReference[] srl = context.getServiceReferences(KernelServiceLocal.class.getName(), null);
			for (int i = 0; srl != null && i < srl.length; i++) {
				this.serviceChanged(new ServiceEvent(ServiceEvent.REGISTERED, srl[i]));
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
//		registerKernelService();
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
				if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.kernel.KernelServiceLocal")) {
					registerKernelService();
				}
			} else if (event.getType() == ServiceEvent.UNREGISTERING) {
				log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " unregistered.");
				kernel = null;
			} else if (event.getType() == ServiceEvent.MODIFIED) {
				log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " modified.");
				if (objectClass[0].equalsIgnoreCase("de.fhb.sd.api.kernel.KernelServiceLocal")) {
					registerKernelService();
				}
			}
		}
	}

	private void log(String log) {
		LOG.log(Level.INFO, "{0}: {1}", new Object[]{bundleName, log});
	}

	private void registerKernelService() {
		log("Registering KernelService");
		try {
			kernel = (KernelServiceLocal) bundleContext.getService(bundleContext.getServiceReference(KernelServiceLocal.class.getName()));
		} catch (NullPointerException | ServiceUnavailableException e) {
			log("ServiceUnavailableException: Twitter-Bundle unavailable.");
		}
	}

	public KernelServiceLocal getKernel() {
		return kernel;
	}
}
