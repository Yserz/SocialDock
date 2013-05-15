/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb.ui.allbundles;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.adminweb.ui.SDAdminWebUI;
import de.fhb.sd.adminweb.ui.allbundles.component.AllBundleTableWithDetails;
import de.fhb.sd.adminweb.ui.mainview.component.AdminTree;
import de.fhb.sd.adminweb.ui.mainview.component.TopMenuBar;
import de.fhb.sd.api.kernel.KernelServiceLocal;

/**
 *
 * @author MacYser
 */
public class AllBundlesView extends CustomComponent implements View {

	private KernelServiceLocal kernel;
	private SDAdminWebUI ui;
	/* define Layout objects */
	private HorizontalSplitPanel horizontal = new HorizontalSplitPanel();
	private VerticalSplitPanel vertical = new VerticalSplitPanel();
	/* define Components */
	private AllBundleTableWithDetails content;
	private TopMenuBar topMenuBar;
	private AdminTree adminTree;

	public AllBundlesView(final KernelServiceLocal kernel) {
		super();
		this.ui = (SDAdminWebUI) getUI();
		this.kernel = kernel;

		vertical.setSizeFull();
		vertical.setSplitPosition(3, Unit.PERCENTAGE);
		vertical.setLocked(true);
		horizontal.setSizeFull();
		horizontal.setSplitPosition(20, Unit.PERCENTAGE);

		fillLayout();

		setCompositionRoot(vertical);
		setSizeFull();
	}

	private void fillLayout() {
		topMenuBar = new TopMenuBar();
		adminTree = new AdminTree();
		content = new AllBundleTableWithDetails(kernel);

		vertical.addComponent(topMenuBar);
		vertical.addComponent(horizontal);

		horizontal.addComponent(adminTree);
		horizontal.addComponent(content);
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		Notification.show("Entered View: " + event.getViewName());
	}
}