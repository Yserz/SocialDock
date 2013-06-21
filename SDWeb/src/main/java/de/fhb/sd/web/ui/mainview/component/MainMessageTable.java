/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.web.ui.mainview.component;

import com.vaadin.data.Item;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import de.fhb.sd.api.golem.GolemLocal;
import de.fhb.sd.api.nyt.NewYorkTimesLocal;
import de.fhb.sd.api.twitter.TwitterLocal;
import de.fhb.sd.domain.entity.GolemMessage;
import de.fhb.sd.domain.entity.Message;
import de.fhb.sd.domain.entity.NewYorkTimesMessage;
import de.fhb.sd.domain.entity.TwitterMessage;
import de.fhb.sd.web.ui.golem.component.GolemDetailPanel;
import de.fhb.sd.web.ui.nyt.component.NYTDetailPanel;
import de.fhb.sd.web.ui.twitterview.component.TwitterDetailPanel;
import de.fhb.sd.web.ui.util.DetailPanel;
import de.fhb.sd.web.ui.util.MessageTable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMessageTable extends MessageTable {

    private static final Logger LOG = Logger.getLogger(MainMessageTable.class.getName());
    private final String TABLEHEADER_AUTHOR = "Author";
    private final String TABLEHEADER_MESSAGE = "Message";
    private final String TABLEHEADER_IMAGE = "Image";
    private TwitterLocal twitter;
    private NewYorkTimesLocal nyt;
    private GolemLocal golem;

    public MainMessageTable(final TwitterLocal twitter, final NewYorkTimesLocal nyt, final GolemLocal golem) {
        super(new NYTDetailPanel(null));
        this.twitter = twitter;
        this.nyt = nyt;
        this.golem = golem;
        addData();
    }

    @Override
    protected List<ColumnHeader> addColumnHeader() {
        List<ColumnHeader> columnList = new ArrayList();
        columnList.add(new ColumnHeader(TABLEHEADER_IMAGE, Image.class));
        columnList.add(new ColumnHeader(TABLEHEADER_AUTHOR, String.class));
        columnList.add(new ColumnHeader(TABLEHEADER_MESSAGE, String.class));
        return columnList;
    }

    @Override
    protected DetailPanel getNewDetailPanel() {
        if (selectedMessage instanceof NewYorkTimesMessage) {
            return new NYTDetailPanel(selectedMessage);
        }
        if (selectedMessage instanceof TwitterMessage) {
            return new TwitterDetailPanel(selectedMessage);
        }
        if (selectedMessage instanceof GolemMessage) {
            return new GolemDetailPanel(selectedMessage);
        }
        return null;
    }

    @Override
    protected void addData() {
        try {
            messageTable.removeAllItems();
            List<Message> allMessages = new ArrayList<>();
            if (twitter != null) {
                allMessages.addAll(twitter.getMessages());
            }
            if (nyt != null) {
                allMessages.addAll(nyt.getMessages());
            }
            if (golem != null) {
                allMessages.addAll(golem.getMessages());
            }
            for (Message message : allMessages) {
                ExternalResource resource = new ExternalResource("");
                if (message instanceof TwitterMessage) {
                    resource = new ExternalResource(((TwitterMessage) message).getProfileImageUrlOfUser());
                } else if (message instanceof NewYorkTimesMessage) {
                    resource = new ExternalResource(((NewYorkTimesMessage) message).getMediaURL());
                } else if (message instanceof GolemMessage) {
                    resource = new ExternalResource(((GolemMessage) message).getImageURLSmall());
                }

                Image img = new Image();
                img.setSource(resource);
                img.setWidth(45, Unit.PIXELS);
                img.setHeight(45, Unit.PIXELS);

                Item item = messageTable.addItem(message);
                item.getItemProperty(TABLEHEADER_IMAGE).setValue(img);
                item.getItemProperty(TABLEHEADER_AUTHOR).setValue(message.getAuthor());
                item.getItemProperty(TABLEHEADER_MESSAGE).setValue(message.getMessage());
            }
        } catch (NullPointerException e) {
            LOG.log(Level.INFO, "Nullpointer in TwitterMessageTable addData()!");
        }
    }
}
