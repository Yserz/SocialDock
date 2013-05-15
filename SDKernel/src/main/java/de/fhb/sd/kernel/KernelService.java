package de.fhb.sd.kernel;

import de.fhb.sd.api.kernel.KernelServiceLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

/**
 * This class implements a simple bundle that utilizes the OSGi framework's
 * event mechanism to listen for service events. Upon receiving a service event,
 * it prints out the event's details.
 *
 */
@Stateless
public class KernelService implements KernelServiceLocal, BundleActivator, ServiceListener, BundleListener, FrameworkListener, Serializable {

	private final static Logger LOG = Logger.getLogger(KernelService.class.getName());
	private BundleContext bundleContext;
	private String bundleName;
	private BundleRegistry bundleRegistry;

	public KernelService() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		bundleRegistry = new BundleRegistry(context);
		bundleName = bundleContext.getBundle().getSymbolicName();
		context.addFrameworkListener(this);
		context.addBundleListener(this);
		context.addServiceListener(this);
		context.registerService(KernelServiceLocal.class.getName(), this, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		context.ungetService(context.getServiceReference(KernelServiceLocal.class.getName()));
		context.removeServiceListener(this);
		context.removeBundleListener(this);
		context.removeFrameworkListener(this);
	}

	@Override
	public void serviceChanged(ServiceEvent event) {

		String[] objectClass = (String[]) event.getServiceReference().getProperty("objectClass");
		String eventBundleName = event.getServiceReference().getBundle().getSymbolicName();

		if (event.getType() == ServiceEvent.REGISTERED) {
			log("Service in Bundle " + eventBundleName + " of type " + objectClass[0] + " registered.");
		} else if (event.getType() == ServiceEvent.UNREGISTERING) {
			log("Service in Bundle " + eventBundleName + " of type " + objectClass[0] + " unregistered.");
		} else if (event.getType() == ServiceEvent.MODIFIED) {
			log("Service in Bundle " + eventBundleName + " of type " + objectClass[0] + " modified.");
		}
	}

	@Override
	public void frameworkEvent(FrameworkEvent event) {
		Throwable throwable = event.getThrowable();
		if (event.getType() == FrameworkEvent.ERROR) {
			log("ERROR: " + throwable);
		} else if (event.getType() == FrameworkEvent.INFO) {
			log("INFO: " + throwable);
		} else if (event.getType() == FrameworkEvent.WARNING) {
			log("WARNING: " + throwable);
		} else if (event.getType() == FrameworkEvent.STARTED) {
			log("STARTED: " + throwable);
		} else if (event.getType() == FrameworkEvent.STOPPED) {
			log("STOPPED: " + throwable);
		} else if (event.getType() == FrameworkEvent.STARTLEVEL_CHANGED) {
			log("STARTLEVEL_CHANGED: " + throwable);
		} else if (event.getType() == FrameworkEvent.STOPPED_BOOTCLASSPATH_MODIFIED) {
			log("STOPPED_BOOTCLASSPATH_MODIFIED: " + throwable);
		} else if (event.getType() == FrameworkEvent.STOPPED_UPDATE) {
			log("STOPPED_UPDATE: " + throwable);
		} else if (event.getType() == FrameworkEvent.WAIT_TIMEDOUT) {
			log("WAIT_TIMEDOUT: " + throwable);
		}
	}

	@Override
	public void bundleChanged(BundleEvent event) {
		String eventBundleName = event.getBundle().getSymbolicName();

		if (event.getType() == BundleEvent.INSTALLED) {
			log(eventBundleName + " Installed");
			registerBundle(event.getBundle());
		} else if (event.getType() == BundleEvent.UNINSTALLED) {
			log(eventBundleName + " Uninstalled");
			unregisterBundle(event.getBundle());
		} else if (event.getType() == BundleEvent.STARTING) {
			log(eventBundleName + " Starting");
		} else if (event.getType() == BundleEvent.STARTED) {
			log(eventBundleName + " Started");
		} else if (event.getType() == BundleEvent.STOPPING) {
			log(eventBundleName + " Stopping");
		} else if (event.getType() == BundleEvent.STOPPED) {
			log(eventBundleName + " Stopped");
		} else if (event.getType() == BundleEvent.UPDATED) {
			log(eventBundleName + " Updated");
		} else if (event.getType() == BundleEvent.RESOLVED) {
			log(eventBundleName + " Resolved");
		} else if (event.getType() == BundleEvent.UNRESOLVED) {
			log(eventBundleName + " Unresolved");
		} else if (event.getType() == BundleEvent.LAZY_ACTIVATION) {
			log(eventBundleName + " Lazy Activation");
		}
	}

	private void log(String log) {
		LOG.log(Level.INFO, "{0}: {1}", new Object[]{bundleName, log});
	}

	@Override
	public List<Bundle> getAllBundels() {
		return Arrays.asList(bundleContext.getBundles());
	}

	@Override
	public List<Bundle> getBundels() {
		return bundleRegistry.getBundles();
	}

	@Override
	public Bundle getBundle(String bundleName) {
		return bundleContext.getBundle(bundleName);
	}

	@Override
	public void startBundle(Bundle bundle) throws BundleException {
		bundle.start();
	}

	@Override
	public void stopBundle(Bundle bundle) throws BundleException {
		bundle.stop();
	}

	@Override
	public void registerBundle(Bundle bundle) {
		bundleRegistry.registerBundle(bundle);
	}

	@Override
	public void unregisterBundle(Bundle bundle) {
		bundleRegistry.unregisterBundle(bundle);
	}
}
