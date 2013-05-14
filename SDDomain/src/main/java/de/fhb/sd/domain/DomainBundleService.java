package de.fhb.sd.domain;

import de.fhb.sd.api.kernel.KernelServiceLocal;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

public class DomainBundleService implements BundleActivator, ServiceListener {

	private final static Logger LOG = Logger.getLogger(DomainBundleService.class.getName());
	private BundleContext bundleContext;
	private String bundleName;
	private KernelServiceLocal kernel;

	public DomainBundleService() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		kernel = bundleContext.getService(bundleContext.getServiceReference(KernelServiceLocal.class));
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
		} else if (event.getType() == ServiceEvent.UNREGISTERING) {
			log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " unregistered.");
		} else if (event.getType() == ServiceEvent.MODIFIED) {
			log("Service in Bundle " + bundleName + " of type " + objectClass[0] + " modified.");
		}
	}

	private void log(String log) {
		System.out.println(bundleName + ": " + log);
	}
}
