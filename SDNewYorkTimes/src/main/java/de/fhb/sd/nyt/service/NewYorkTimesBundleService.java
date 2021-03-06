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
package de.fhb.sd.nyt.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import de.fhb.sd.api.nyt.NewYorkTimesLocal;

/**
 * This Bean gets articles from the NewYorkTimes.
 *
 * @author Christoph Ott
 */
@Stateless
public class NewYorkTimesBundleService implements BundleActivator, ServiceListener {

	private final static Logger LOG = Logger.getLogger(NewYorkTimesBundleService.class.getName());
	private BundleContext bundleContext;
	private String bundleName;

	public NewYorkTimesBundleService() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		bundleName = bundleContext.getBundle().getSymbolicName();

		context.addServiceListener(this);
		context.registerService(NewYorkTimesLocal.class.getName(), new NewYorkTimesService(), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		context.ungetService(context.getServiceReference(NewYorkTimesLocal.class.getName()));
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
		System.out.println(bundleName + ": " + log);
	}
}
