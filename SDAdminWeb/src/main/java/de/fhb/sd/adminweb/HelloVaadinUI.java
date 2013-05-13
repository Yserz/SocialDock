/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.fhb.sd.api.kernel.KernelServiceLocal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;
import org.glassfish.osgicdi.OSGiService;
import org.osgi.framework.Bundle;

/**
 *
 * @author MacYser
 */
//@CDIUI
@Title("SocialDockAdmin")
@PreserveOnRefresh
public class HelloVaadinUI extends UI {

	@Inject
	@OSGiService(dynamic = true)
	KernelServiceLocal kernel;
	private Table bundleList = new Table();

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	@Override
	protected void init(VaadinRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
		bundleList.addContainerProperty("Name", String.class, null);
		bundleList.addContainerProperty("ID", String.class, null);
		bundleList.addContainerProperty("Änderungsdatum", String.class, null);
		bundleList.addContainerProperty("Version", String.class, null);
		bundleList.addContainerProperty("Location", String.class, null);

		for (Bundle bundle : kernel.getBundels()) {
			String name = bundle.getSymbolicName();
			String id = "[" + bundle.getBundleId() + "]";
			String lastModified = sdf.format(new Date(bundle.getLastModified()));
			String version = bundle.getVersion() + "";
			String location = bundle.getLocation();

			bundleList.addItem(
					new Object[]{
				name,
				id,
				lastModified,
				version,
				location
			},
					bundle);
		}
		setContent(bundleList);
		bundleList.setSizeFull();
	}
}
