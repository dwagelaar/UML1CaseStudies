
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
 * @param list 
 * @return 
 */
    public im.view.ContactListView createContactListView(im.model.ContactList list) {        
        AWTContactListView view = new AWTContactListView();
        view.setModel(list);
        return view;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 * @return 
 */
    public im.view.ContactView createContactView(im.model.Contact contact) {        
        AWTContactView view = new AWTContactView();
        view.setModel(contact);
        return view;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param conversation 
 * @return 
 */
    public im.view.ConversationView createConversationView(im.model.Conversation conversation) {        
        AWTConversationView view = new AWTConversationView();
        view.setModel(conversation);
        return view;
    } 
 }
