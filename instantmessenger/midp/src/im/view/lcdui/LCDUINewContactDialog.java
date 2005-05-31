
package im.view.lcdui;
import im.view.*;
import java.util.Vector;
import javax.microedition.lcdui.*;

/**
 * <p></p>
 */
public class LCDUINewContactDialog extends javax.microedition.lcdui.Form implements im.view.NewContactDialog {

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.Command okBtn = new Command("OK", Command.OK, 1);

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.Command cancelBtn = new Command("Cancel", Command.CANCEL, 1);

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.TextField uidField = new TextField("User ID", "", 64, TextField.ANY);

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.TextField nameField = new TextField("Name", "", 64, TextField.ANY);

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.ChoiceGroup networkField = new ChoiceGroup("Network", Choice.EXCLUSIVE);

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector listeners = new Vector();

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.lcdui.TextField passwordField = new TextField("Password", "", 64, TextField.PASSWORD);

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  LCDUINewContactDialog() {        
        super("Add new contact");
        append(networkField);
        append(uidField);
        append(nameField);
        append(passwordField);
        addCommand(okBtn);
        addCommand(cancelBtn);
        setCommandListener(new CommandListener() {
            public void commandAction(Command c, Displayable d) {
                if (c.equals(okBtn)) {
                    onOk();
                } else {
                    onCancel();
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
 * @return 
 */
    public String getUid() {        
        return uidField.getString();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getName() {        
        return nameField.getString();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public int getNetwork() {        
        return networkField.getSelectedIndex();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param l 
 */
    public void addListener(im.view.NewContactDialogListener l) {        
        listeners.addElement(l);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void onOk() {        
        notifyListeners(true);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void onCancel() {        
        notifyListeners(false);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param okClicked 
 */
    private void notifyListeners(boolean okClicked) {        
        for (int i = 0; i < listeners.size(); i++) {
            ((NewContactDialogListener) listeners.elementAt(i)).onNewContactDialogClose(okClicked, this);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getPassword() {        
        return passwordField.getString();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param n 
 */
    public void addNetwork(String n) {        
        networkField.append(n, null);
    } 
 }
