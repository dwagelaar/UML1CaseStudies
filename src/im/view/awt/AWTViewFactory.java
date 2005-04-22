
package im.view.awt;

/**
 * <p></p>
 */
public class AWTViewFactory implements im.view.ViewFactory {

/**
 * <p>Represents ...</p>
 */
    private im.view.awt.AWTContactList window = null;

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
        this.window = view.getImpl();
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
        view.setWindow(this.window);
        return view;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param message 
 * @return 
 */
    public im.view.MessageView createMessageView(im.model.Message message) {        
        AWTMessageView view = new AWTMessageView();
        view.setModel(message);
        return view;
    } 
 }
