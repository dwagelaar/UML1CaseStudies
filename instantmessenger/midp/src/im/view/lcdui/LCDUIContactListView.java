
package im.view.lcdui;
import im.view.*;
import java.util.Vector;
import javax.microedition.lcdui.*;

/**
 * <p></p>
 */
public class LCDUIContactListView extends javax.microedition.lcdui.List implements im.view.ContactListView {

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.Command addContactBtn = new Command("Add", Command.SCREEN, 1);

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.Command removeContactBtn = new Command("Remove", Command.ITEM, 1);

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.Command contactActionBtn = new Command("Message", Command.ITEM, 1);

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector listeners = new Vector();

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.Command exitBtn = new Command("Exit", Command.EXIT, 1);

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  LCDUIContactListView() {        
        super("Instant Messenger", Choice.IMPLICIT);
        addCommand(addContactBtn);
        addCommand(contactActionBtn);
        addCommand(removeContactBtn);
        addCommand(exitBtn);
        setCommandListener(new CommandListener() {
            public void commandAction(Command c, Displayable d) {
                if (c.equals(addContactBtn)) {
                    notifyAddClicked();
                } else if (c.equals(removeContactBtn)) {
                    notifyRemoveClicked();
                } else if (c.equals(contactActionBtn)) {
                    notifyContactAction();
                } else {
                    im.InstantMessagingClient.getInstance().notifyDestroyed();
                }
            }
        });
        im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
        Display.getDisplay(client).setCurrent(this);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 * @param index 
 */
    public void addContact(im.model.Contact contact, int index) {        
        String name = contact.getName();
        String status = contact.getStatus();
        if (contact instanceof im.model.Identity) {
            name = "* " + name;
        } else {
            name = "  " + name;
        }
        if (contact.getNetwork() != null) {
            name += " - " + contact.getNetwork().getName();
        }
        if (status == null) {
            status = "offline";
        }
        name += " (" + status + ")";
        insert(index, name, null);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param index 
 */
    public void removeContact(int index) {        
        delete(index);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public int getSelectedContact() {        
        return getSelectedIndex();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param l 
 */
    public void addListener(im.view.ContactListViewListener l) {        
        listeners.addElement(l);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void notifyAddClicked() {        
        for (int i = 0; i < listeners.size(); i++) {
            ((ContactListViewListener) listeners.elementAt(i)).onContactListAdd();
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void notifyRemoveClicked() {        
        for (int i = 0; i < listeners.size(); i++) {
            ((ContactListViewListener) listeners.elementAt(i)).onContactListRemove();
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void notifyContactAction() {        
        for (int i = 0; i < listeners.size(); i++) {
            ((ContactListViewListener) listeners.elementAt(i)).onContactListAction();
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param enabled 
 */
    public void setEnabled(boolean enabled) {        
        if (enabled) {
            im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
            Display.getDisplay(client).setCurrent(this);
        }
    } 
 }
