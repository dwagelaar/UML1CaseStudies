
package im.view.lcdui;

/**
 * <p></p>
 */
public class LCDUIViewFactory extends im.view.ViewFactory {

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.ContactListView createContactListView() {        
        return new LCDUIContactListView();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.NewContactDialog createNewContactDialog() {        
        return new LCDUINewContactDialog();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.ConversationView createConversationView() {        
        return new LCDUIConversationView();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @throws ClassNotFoundException 
 */
    public  LCDUIViewFactory() throws ClassNotFoundException {        
        // fail factory creation if LCDUI not available
        Class.forName("javax.microedition.lcdui.Display");
    } 
 }
