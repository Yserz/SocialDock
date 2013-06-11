package de.fhb.sd.web.ui.golem.component;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import de.fhb.sd.domain.entity.GolemMessage;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.web.ui.util.DetailPanel;

public class GolemDetailPanel extends DetailPanel {

	public GolemDetailPanel(Message selectedMessage) {
		super(selectedMessage);
	}

    @Override
    protected Panel addDetailContent() {
        Panel panel = new Panel();
        panel.setSizeFull();

        GridLayout infoGrid = new GridLayout(3, 5);

        if (selectedMessage != null) {
            int row = 0;
            GolemMessage golemM = (GolemMessage) selectedMessage;
            if (!golemM.getImageURLMedium().isEmpty()) {
                ExternalResource resource = new ExternalResource(golemM.getImageURLMedium());
                Image img = new Image();
                img.setSource(resource);
                infoGrid.addComponent(img, 0,0,0,4); // rowspan
            }

            Label component = new Label("Title: ");
            component.setWidth(100, Unit.PIXELS);
            infoGrid.addComponent(component,1,row);
            Label titleLabel = new Label(golemM.getTitle());
            infoGrid.addComponent(titleLabel,2,row++);

            infoGrid.addComponent(new Label("Author: "),1,row);
            Label authorLabel = new Label(golemM.getAuthor());
            infoGrid.addComponent(authorLabel,2,row++);

            infoGrid.addComponent(new Label("Published: "),1,row);
            Label publishedLabel = new Label(golemM.getPublished() + "");
            infoGrid.addComponent(publishedLabel,2,row++);

            infoGrid.addComponent(new Label("Message: "),1,row);
            Label messageLabel = new Label(golemM.getMessage());
            infoGrid.addComponent(messageLabel,2,row++);

            ExternalResource linkResource = new ExternalResource(golemM.getURL());
            infoGrid.addComponent(new Label("URL: "),1,row);
            Link urlLink = new Link(golemM.getURL(), linkResource);
            infoGrid.addComponent(urlLink,2,row++);
        }

        panel.setContent(infoGrid);
        return panel;
    }
}
