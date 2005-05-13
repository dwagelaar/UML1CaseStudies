
package im.view.lcdui;
import im.view.*;
import java.util.Vector;
import javax.microedition.lcdui.*;

/**
 * <p></p>
 */
public class LCDUIConversationView extends javax.microedition.lcdui.Form implements im.view.ConversationView {

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.TextField messageField = new TextField("", "", 256, TextField.ANY);

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.Command sendBtn = new Command("Send", Command.SCREEN, 1);

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.Command closeBtn = new Command("Close", Command.BACK, 1);

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.StringItem emptyField = new StringItem("", "");

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector listeners = new Vector();

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.Displayable previousScreen;

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  LCDUIConversationView() {        
        super("Message");
        append(emptyField);
        addCommand(sendBtn);
        addCommand(closeBtn);
        setCommandListener(new CommandListener() {
            public void commandAction(Command c, Displayable d) {
                if (c.equals(sendBtn)) {
                    notifySendClicked();
                } else {
                    notifyClose();
                }
            }
        });
        im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
        previousScreen = Display.getDisplay(client).getCurrent();
        Display.getDisplay(client).setCurrent(this);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public Object getContent() {        
        return messageField.getString();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param content 
 */
    public void addContent(Object content) {        
        if (content instanceof String) {
            append(content.toString() + "\n");
        } else if (content instanceof Image) {
            Image im = (Image) content;
            append(im);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param enabled 
 */
    public void setTextEnabled(boolean enabled) {        
        if (enabled) {
            set(0, messageField);
        } else {
            set(0, emptyField);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param l 
 */
    public void addListener(im.view.ConversationViewListener l) {        
        listeners.addElement(l);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void notifyClose() {        
        for (int i = 0; i < listeners.size(); i++) {
            ((ConversationViewListener) listeners.elementAt(i)).onConversationClose();
        }
        im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
        Display.getDisplay(client).setCurrent(previousScreen);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void notifySendClicked() {        
        for (int i = 0; i < listeners.size(); i++) {
            ((ConversationViewListener) listeners.elementAt(i)).onConversationSend();
        }
        messageField.setString("");
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void toFront() {        
        im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
        Display.getDisplay(client).setCurrent(this);
    } 
 }
