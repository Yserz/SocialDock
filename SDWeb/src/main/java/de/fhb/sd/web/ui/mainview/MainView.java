/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.mainview;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;
import de.fhb.sd.web.WebBundleService;
import de.fhb.sd.web.ui.mainview.component.MainMessageTable;
import de.fhb.sd.web.ui.mainview.component.TopMenuBar;
import de.fhb.sd.web.ui.util.MessageTableWithDetails;

/**
 * @author MacYser
 */
public class MainView extends CustomComponent implements View {

    /* define Layout objects */
    private VerticalSplitPanel vertical = new VerticalSplitPanel();
    /* define Components */
    private MessageTableWithDetails content;
    private TopMenuBar topMenuBar;

    public MainView() {
        super();

        vertical.setSizeFull();
        vertical.setSplitPosition(3, Unit.PERCENTAGE);
        vertical.setLocked(true);

        fillLayout();

        setCompositionRoot(vertical);
        setSizeFull();
    }

    private void fillLayout() {
        vertical.removeAllComponents();
        topMenuBar = new TopMenuBar();
        vertical.addComponent(topMenuBar);
        content = new MessageTableWithDetails(new MainMessageTable(((WebBundleService) UI.getCurrent()).getTwitter(), ((WebBundleService) UI.getCurrent()).getNyt(), ((WebBundleService) UI.getCurrent()).getGolem()));
        vertical.addComponent(content);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Entered View: " + event.getViewName());
    }
}
