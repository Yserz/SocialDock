/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb.ui;

import com.vaadin.ui.Table;
import de.fhb.sd.api.kernel.KernelServiceLocal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.osgi.framework.Bundle;

/**
 *
 * @author MacYser
 */
public class BundleTable extends Table {

	private KernelServiceLocal kernel;

	public BundleTable(final KernelServiceLocal kernel) {
		super();
		this.kernel = kernel;
		init();
	}

	private void init() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
		addContainerProperty("Name", String.class, null);
		addContainerProperty("ID", String.class, null);
		addContainerProperty("Änderungsdatum", String.class, null);
		addContainerProperty("Version", String.class, null);
		addContainerProperty("Location", String.class, null);

		for (Bundle bundle : kernel.getBundels()) {
			String name = bundle.getSymbolicName();
			String id = "[" + bundle.getBundleId() + "]";
			String lastModified = sdf.format(new Date(bundle.getLastModified()));
			String version = bundle.getVersion() + "";
			String location = bundle.getLocation();

			addItem(
					new Object[]{
				name,
				id,
				lastModified,
				version,
				location
			},
					bundle);
		}
		setHeight(100, Unit.PERCENTAGE);

	}
}
