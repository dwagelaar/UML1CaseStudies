
package im.view.awt;

/**
 * <p></p>
 */
public class AWTViewFactory extends im.view.ViewFactory {

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.ContactListView createContactListView() {        
        return new AWTContactListView();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.NewContactDialog createNewContactDialog() {        
        return new AWTNewContactDialog();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.ConversationView createConversationView() {        
        return new AWTConversationView();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @throws ClassNotFoundException 
 */
    public  AWTViewFactory() throws ClassNotFoundException {        
        // fail factory creation if AWT not available
        Class.forName("java.awt.Toolkit");
    } 
 }
