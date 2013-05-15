/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.kernel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 *
 * @author MacYser
 */
public class BundleRegistry {

	private Map<String, Long> bundleRegistry;
	private BundleContext bundleContext;
	private String[] bundleNames = new String[]{
		"SDKernel",
		"SDAPI",
		"SDDomain",
		"SDAdminWeb",
		"SDWeb",
		"SDGoogle",
		"SDNewYorkTimes",
		"SDTwitter"
	};

	public BundleRegistry(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
		bundleRegistry = new HashMap<>(7);
		for (String bundle : bundleNames) {
			bundleRegistry.put(bundle, -1L);
		}
		updateRegister();
	}

	private void updateRegister() {
		for (Bundle bundle : bundleContext.getBundles()) {
			registerBundle(bundle);
		}
	}

	public List<Bundle> getBundles() {
		List<Bundle> bundles = new ArrayList<>();
		for (Map.Entry<String, Long> bundle : bundleRegistry.entrySet()) {
			if (bundle.getValue() > 0) {
				bundles.add(bundleContext.getBundle(bundle.getValue()));
			}
		}
		return bundles;
	}

	public boolean registerBundle(Bundle bundle) {
		boolean ret = false;
		String bundleName = bundle.getSymbolicName();
		Long bundleId = bundle.getBundleId();

		if (bundleRegistry.containsKey(bundleName)) {
			if (bundleRegistry.get(bundleName).longValue() < 0) {
				bundleRegistry.put(bundleName, bundleId);
				ret = true;
			} else {
				log("Bundle ist bereits in der BundleRegistry registriert.");
			}
		}
		return ret;
	}

	public boolean unregisterBundle(Bundle bundle) {
		boolean ret = false;
		String bundleName = bundle.getSymbolicName();
		Long bundleId = bundle.getBundleId();

		if (bundleRegistry.containsKey(bundleName)) {
			if (bundleRegistry.get(bundleName).longValue() == bundleId) {
				bundleRegistry.put(bundleName, -1L);
				ret = true;
			} else {
				log("BundleID stimmt nicht mit ID in der BundleRegistry überein.");
			}
		} else {
			log("Bundle ist nicht in der BundleRegistry.");
		}
		return ret;
	}

	public int countBundles() {
		int count = 0;
		for (Map.Entry<String, Long> bundle : bundleRegistry.entrySet()) {
			if (bundle.getValue() > 0) {
				count++;
			}
		}
		log(count + "/" + bundleNames.length + " Bundles are registered.");
		return count;
	}

	public int countAllBundles() {
		int count = bundleContext.getBundles().length;
		log(count + " Bundles are registered.");
		return count;
	}

	private void log(String log) {
		System.out.println(bundleContext.getBundle().getSymbolicName() + ": " + log);
	}
}
