
package de.fhb.sd.golem.service;

import de.fhb.sd.api.golem.GolemLocal;
import de.fhb.sd.api.twitter.TwitterLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

/**
 * This Bean connects to Twitter and streams messages.
 *
 * @author MChristoph Ott
 */
@Stateless
public class GolemBundleService implements BundleActivator, ServiceListener {

	private final static Logger LOG = Logger.getLogger(GolemBundleService.class.getName());
	private BundleContext bundleContext;
	private String bundleName;

	public GolemBundleService() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		bundleName = bundleContext.getBundle().getSymbolicName();

		context.addServiceListener(this);
		GolemLocal golem = new GolemService();
		context.registerService(GolemLocal.class.getName(), golem, null);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		((GolemLocal) bundleContext.getService(bundleContext.getServiceReference(GolemLocal.class.getName()))).stop();
		context.ungetService(context.getServiceReference(GolemLocal.class.getName()));
		context.removeServiceListener(this);
	}

	@Override
	public void serviceChanged(ServiceEvent event) {

		String[] objectClass = (String[]) event.getServiceReference().getProperty("objectClass");

		if (objectClass[0].contains("de.fhb.sd")) {
			if (event.getType() == ServiceEvent.REGISTERED) {
				log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " registered.");
			} else if (event.getType() == ServiceEvent.UNREGISTERING) {
				log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " unregistered.");
			} else if (event.getType() == ServiceEvent.MODIFIED) {
				log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " modified.");
			}
		}
	}

	private void log(String log) {
		LOG.log(Level.INFO, "{0}: {1}", new Object[]{bundleName, log});
	}
}
