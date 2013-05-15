/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.adminweb.ui.aboutview.AboutView;
import de.fhb.sd.adminweb.ui.allbundles.AllBundlesView;
import de.fhb.sd.adminweb.ui.mainview.component.AdminTree;
import de.fhb.sd.adminweb.ui.mainview.component.BundleTableWithDetails;
import de.fhb.sd.adminweb.ui.mainview.MainView;
import de.fhb.sd.adminweb.ui.mainview.component.TopMenuBar;
import de.fhb.sd.api.kernel.KernelServiceLocal;
import javax.inject.Inject;
import org.glassfish.osgicdi.OSGiService;

/**
 *
 * @author MacYser
 */
@Title("SocialDockAdmin")
@PreserveOnRefresh
public class SDAdminWebUI extends UI {

	public final String MAIN = "";
	public final String ABOUT = "about";
	public final String ALL = "all";
	@Inject
	@OSGiService(dynamic = false)
	private KernelServiceLocal kernel;
	private Navigator nav = new Navigator(this, this);
	private MainView mainView;
	private AllBundlesView allBundlesView;
	private AboutView aboutView;

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	@Override
	protected void init(final VaadinRequest request) {
		mainView = new MainView(kernel);
		nav.addView(MAIN, mainView);

		allBundlesView = new AllBundlesView(kernel);
		nav.addView(ALL, allBundlesView);

		aboutView = new AboutView();
		nav.addView(ABOUT, aboutView);

		navTo(MAIN);
	}

	public KernelServiceLocal getKernel() {
		return kernel;
	}

	public void navTo(String to) {
		nav.navigateTo(to);
	}
}
