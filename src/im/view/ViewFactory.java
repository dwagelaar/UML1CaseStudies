
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
 * @return 
 */
    public abstract im.view.ContactListView createContactListView();
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract im.view.NewContactDialog createNewContactDialog();
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract im.view.ConversationView createConversationView();
}









