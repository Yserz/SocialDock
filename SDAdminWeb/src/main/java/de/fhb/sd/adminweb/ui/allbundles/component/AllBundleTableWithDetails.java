/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.adminweb.ui.allbundles.component;

import com.vaadin.data.Property;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.api.kernel.KernelServiceLocal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.osgi.framework.Bundle;

/**
 *
 * @author MacYser
 */
public class AllBundleTableWithDetails extends CustomComponent {

	private KernelServiceLocal kernel;
	private Bundle selectedBundle;
	private DetailPanel detail;
	private BundleTable table;
	private VerticalSplitPanel content = new VerticalSplitPanel();
	private final SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	private Map<Integer, String> mapBundleStatus;

	public AllBundleTableWithDetails(final KernelServiceLocal kernel) {
		super();
		this.kernel = kernel;



		mapBundleStatus = new HashMap<Integer, String>(10);
		mapBundleStatus.put(1, "INSTALLED");
		mapBundleStatus.put(2, "STARTED");
		mapBundleStatus.put(3, "STOPPED");
		mapBundleStatus.put(4, "UPDATED");
		mapBundleStatus.put(16, "UNINSTALLED");
		mapBundleStatus.put(32, "RESOLVED");
		mapBundleStatus.put(64, "UNRESOLVED");
		mapBundleStatus.put(128, "STARTING");
		mapBundleStatus.put(256, "STOPPING");
		mapBundleStatus.put(512, "LAZY_ACTIVATION");


		content.setSizeFull();
		content.setSplitPosition(70, Unit.PERCENTAGE);

		detail = new DetailPanel();
		table = new BundleTable(kernel);
//		table.markAsDirtyRecursive();
		new Thread(table).start();
		content.addComponent(table);

		content.addComponent(detail);
		setCompositionRoot(content);
		setSizeFull();

	}

//	class InitializerThread extends Thread {
//
//		@Override
//		public void run() {
//			// Init done, update the UI after doing locking
//			while (true) {
//				if (getUI() != null) {
//					getUI().access(new Runnable() {
//						@Override
//						public void run() {
//							try {
//								Thread.sleep(10000);
//								table.updateTable();
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					});
//				}
//			}
//		}
//	}
	public void updateDetailPanel() {
		DetailPanel newDetail = new DetailPanel();
		content.replaceComponent(detail, newDetail);
		detail = newDetail;
	}

	private class BundleTable extends Table implements Runnable {

		private final String[] fieldNames = new String[]{"Name", "ID", "Status", "Änderungsdatum", "Version", "Location"};

		public BundleTable(final KernelServiceLocal kernel) {
			super();
			init();
		}

		public void updateTable() {
			addData();
			refreshRowCache();
			refreshRenderedCells();
		}

		private void init() {
			addHeader();
			addData();

			setSelectable(true);
			setImmediate(true);
			setHeight(100, Unit.PERCENTAGE);

			addValueChangeListener(new ValueChangeListener() {
				@Override
				public void valueChange(Property.ValueChangeEvent event) {
					Notification.show("Item clicked: " + event.getProperty().getValue());
					selectedBundle = (Bundle) event.getProperty().getValue();
					updateDetailPanel();
				}
			});
		}

		private void addHeader() {
			for (String field : fieldNames) {
				addContainerProperty(field, String.class, "");
			}
		}

		private void addData() {
			removeAllItems();
			for (Bundle bundle : kernel.getAllBundels()) {
				String[] bundleAtt = new String[]{
					bundle.getSymbolicName(),
					"[" + bundle.getBundleId() + "]",
					mapBundleStatus.get(bundle.getState()),
					sdf.format(new Date(bundle.getLastModified())),
					bundle.getVersion() + "",
					bundle.getLocation()
				};

				addItem(bundleAtt, bundle);
//				Object itemId = container.addItem();
//				for (int i = 0; i < bundleAtt.length; i++) {
//					container.getContainerProperty(itemId, fieldNames[i]).setValue(bundleAtt[i]);
//				}

			}
		}

		@Override
		public void run() {
			boolean loop = true;

			while (loop) {
				try {
					Thread.sleep(30000);
					//update the data in table
					updateTable();

//					markAsDirty();
//					markAsDirtyRecursive();
					refreshRowCache();
//					refreshRenderedCells();
				} catch (InterruptedException | IllegalStateException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					loop = false;
				}
			}

		}
	}

	private class DetailPanel extends CustomComponent {

		private VerticalLayout vertical = new VerticalLayout();
//		private HorizontalLayout buttonLine = new HorizontalLayout();

		public DetailPanel() {
			super();
			setCompositionRoot(vertical);
			vertical.setMargin(true);
			setSizeFull();

			init();
		}

		private void init() {

//			Button toggleActive = new Button("Aktivieren");
//			if (selectedBundle != null) {
//				switch (mapBundleStatus.get(selectedBundle.getState())) {
//					case "RESOLVED":
//						toggleActive.setCaption("Deaktivieren");
//						toggleActive.addClickListener(new Button.ClickListener() {
//							@Override
//							public void buttonClick(Button.ClickEvent event) {
//								try {
//									kernel.stopBundle(selectedBundle);
//								} catch (BundleException ex) {
//									Logger.getLogger(BundleTableWithDetails.class.getName()).log(Level.SEVERE, null, ex);
//								}
//							}
//						});
//						break;
//					case "STARTED":
//						toggleActive.setCaption("Aktivieren");
//						toggleActive.addClickListener(new Button.ClickListener() {
//							@Override
//							public void buttonClick(Button.ClickEvent event) {
//								try {
//									kernel.startBundle(selectedBundle);
//								} catch (BundleException ex) {
//									Logger.getLogger(BundleTableWithDetails.class.getName()).log(Level.SEVERE, null, ex);
//								}
//							}
//						});
//						break;
//					case "UPDATED":
//						toggleActive.setCaption("Aktivieren");
//						toggleActive.addClickListener(new Button.ClickListener() {
//							@Override
//							public void buttonClick(Button.ClickEvent event) {
//								try {
//									kernel.startBundle(selectedBundle);
//								} catch (BundleException ex) {
//									Logger.getLogger(BundleTableWithDetails.class.getName()).log(Level.SEVERE, null, ex);
//								}
//							}
//						});
//						break;
//					default:
//						toggleActive.setCaption(" - ");
//						toggleActive.setEnabled(false);
//						break;
//				}
//			}
//
//			buttonLine.addComponent(toggleActive);
//
//			vertical.addComponent(buttonLine);

			GridLayout infoGrid = new GridLayout(2, 4);
			infoGrid.setMargin(true);
			infoGrid.addComponent(new Label("Name: "));
			infoGrid.addComponent(new Label(selectedBundle != null ? selectedBundle.getSymbolicName() : ""));

			infoGrid.addComponent(new Label("ID: "));
			infoGrid.addComponent(new Label(selectedBundle != null ? "" + selectedBundle.getBundleId() : ""));

			infoGrid.addComponent(new Label("Status: "));
			infoGrid.addComponent(new Label(selectedBundle != null ? mapBundleStatus.get(selectedBundle.getState()) : ""));

			infoGrid.addComponent(new Label("Änderungsdatum: "));
			infoGrid.addComponent(new Label(selectedBundle != null ? sdf.format(new Date(selectedBundle.getLastModified())) : ""));

			infoGrid.addComponent(new Label("Version: "));
			infoGrid.addComponent(new Label(selectedBundle != null ? selectedBundle.getVersion().toString() : ""));

			vertical.addComponent(infoGrid);
		}
	}
}
