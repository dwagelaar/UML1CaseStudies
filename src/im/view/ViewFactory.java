
package im.view;
/**
 * <p></p>
 */
public interface ViewFactory {
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param message 
 * @return 
 */
    public abstract im.view.MessageView createMessageView(im.model.Message message);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 * @return 
 */
    public abstract im.view.ContactView createContactView(im.model.Contact contact);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param list 
 * @return 
 */
    public abstract im.view.ContactListView createContactListView(im.model.ContactList list);
}









