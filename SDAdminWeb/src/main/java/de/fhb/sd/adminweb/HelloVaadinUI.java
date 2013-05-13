/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
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
	private MenuBar menu = new MenuBar();
	private Tree tree = new Tree();
	private Label detail = new Label("Detail");

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	@Override
	protected void init(VaadinRequest request) {
		HorizontalSplitPanel horizontal = new HorizontalSplitPanel();
		VerticalSplitPanel vertical = new VerticalSplitPanel();
		VerticalSplitPanel content = new VerticalSplitPanel();

		vertical.setSizeFull();
		vertical.setSplitPosition(3, Unit.PERCENTAGE);
		vertical.setLocked(true);
		horizontal.setSizeFull();
		horizontal.setSplitPosition(20, Unit.PERCENTAGE);

		content.setSizeFull();
		content.setSplitPosition(80, Unit.PERCENTAGE);

		initMenu();
		initTable();
		initTree();


		vertical.addComponent(menu);
		vertical.addComponent(horizontal);

		horizontal.addComponent(tree);
		horizontal.addComponent(content);

		content.addComponent(bundleList);
		content.addComponent(detail);

		setContent(vertical);
	}

	public void initMenu() {
		menu.setSizeFull();
		menu.addItem("test", new MenuBar.Command() {
			@Override
			public void menuSelected(MenuBar.MenuItem selectedItem) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		});
	}

	public void initTree() {
		tree.addItem("Bla");
		tree.setHeight(100, Unit.PERCENTAGE);
	}

	public void initTable() {
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
		bundleList.setHeight(100, Unit.PERCENTAGE);
	}
}
