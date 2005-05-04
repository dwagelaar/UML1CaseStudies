
package im.view.awt;

/**
 * <p></p>
 */
public class AWTViewFactory implements im.view.ViewFactory {

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
 }
