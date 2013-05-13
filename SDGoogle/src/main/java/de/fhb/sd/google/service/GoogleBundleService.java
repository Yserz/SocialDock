/*
 * Copyright (C) 2013 Michael Koppen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.fhb.sd.google.service;

import de.fhb.sd.api.google.GoogleLocal;
import de.fhb.sd.api.kernel.KernelServiceLocal;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

/**
 * This Bean connects to Google and streams messages.
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
@Stateless
@Startup
public class GoogleBundleService implements BundleActivator, ServiceListener {

	private final static Logger LOG = Logger.getLogger(GoogleBundleService.class.getName());
	private BundleContext bundleContext;
	private String bundleName;
	private KernelServiceLocal kernel;

	public GoogleBundleService() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		kernel = bundleContext.getService(bundleContext.getServiceReference(KernelServiceLocal.class));
		bundleName = bundleContext.getBundle().getSymbolicName();

		context.addServiceListener(this);
		kernel.registerBundle(bundleContext.getBundle());
		context.registerService(GoogleLocal.class.getName(), new GoogleService(), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		context.ungetService(context.getServiceReference(GoogleLocal.class.getName()));
		kernel.unregisterBundle(context.getBundle());
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
