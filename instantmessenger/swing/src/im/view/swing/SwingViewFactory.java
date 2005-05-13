
package im.view.swing;

/**
 * <p></p>
 */
public class SwingViewFactory extends im.view.ViewFactory {

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.ContactListView createContactListView() {        
        return new SwingContactListView();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.NewContactDialog createNewContactDialog() {        
        return new SwingNewContactDialog();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.ConversationView createConversationView() {        
        return new SwingConversationView();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @throws ClassNotFoundException 
 */
    public  SwingViewFactory() throws ClassNotFoundException {        
        // fail factory creation if Swing not available
        Class.forName("javax.swing.UIManager");
    } 
 }
