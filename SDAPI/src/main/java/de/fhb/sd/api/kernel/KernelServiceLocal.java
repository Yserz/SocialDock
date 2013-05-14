/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.api.kernel;

import java.util.List;
import javax.ejb.Local;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

/**
 *
 * @author MacYser
 */
@Local
public interface KernelServiceLocal {

	List<Bundle> getAllBundels();

	List<Bundle> getBundels();

	Bundle getBundle(String bundleName);

	void startBundle(Bundle bundle) throws BundleException;

	void stopBundle(Bundle bundle) throws BundleException;

	void registerBundle(Bundle bundle);

	void unregisterBundle(Bundle bundle);
}
