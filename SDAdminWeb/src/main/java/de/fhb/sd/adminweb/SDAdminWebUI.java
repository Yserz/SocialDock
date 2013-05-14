/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.adminweb.ui.AdminTree;
import de.fhb.sd.adminweb.ui.BundleTable;
import de.fhb.sd.adminweb.ui.TopMenuBar;
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

	@Inject
	@OSGiService(dynamic = true)
	KernelServiceLocal kernel;

	/* define Layout objects */
	private HorizontalSplitPanel horizontal = new HorizontalSplitPanel();
	private VerticalSplitPanel vertical = new VerticalSplitPanel();
	private VerticalSplitPanel content = new VerticalSplitPanel();
	/* define Components */
	private BundleTable bundleTable;
	private TopMenuBar topMenuBar;
	private AdminTree adminTree;
	private Label detail = new Label("Detail");

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	@Override
	protected void init(final VaadinRequest request) {
		vertical.setSizeFull();
		vertical.setSplitPosition(3, Unit.PERCENTAGE);
		vertical.setLocked(true);
		horizontal.setSizeFull();
		horizontal.setSplitPosition(20, Unit.PERCENTAGE);

		content.setSizeFull();
		content.setSplitPosition(80, Unit.PERCENTAGE);

		fillLayout();

		setContent(vertical);
	}

	private void fillLayout() {
		bundleTable = new BundleTable(kernel);
		topMenuBar = new TopMenuBar();
		adminTree = new AdminTree();

		vertical.addComponent(topMenuBar);
		vertical.addComponent(horizontal);

		horizontal.addComponent(adminTree);
		horizontal.addComponent(content);

		content.addComponent(bundleTable);
		content.addComponent(detail);
	}
}
