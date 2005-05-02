
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
 * @param l 
 * @return 
 */
    public abstract im.view.ContactListView createContactListView(im.model.ContactList l);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 * @return 
 */
    public abstract im.view.ContactView createContactView(im.model.Contact c);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 * @return 
 */
    public abstract im.view.ConversationView createConversationView(im.model.Conversation c);
}









