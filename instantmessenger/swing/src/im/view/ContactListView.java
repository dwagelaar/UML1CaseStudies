
package im.view;
/**
 * <p></p>
 */
public interface ContactListView {
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 * @param index 
 */
    public abstract void addContact(String c, int index);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param index 
 */
    public abstract void removeContact(int index);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param l 
 */
    public abstract void addListener(im.view.ContactListViewListener l);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract int getSelectedContact();
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param enabled 
 */
    public abstract void setEnabled(boolean enabled);
}









