
package im.view.swing;

/**
 * <p></p>
 */
public class SwingViewFactory implements im.view.ViewFactory {

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
 }
