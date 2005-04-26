
package im.view.awt;

/**
 * <p></p>
 */
public class AWTContactView extends im.view.ContactView {

/**
 * <p>Represents ...</p>
 */
    private im.view.awt.AWTContactList impl = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param impl 
 */
    public void setImpl(im.view.awt.AWTContactList impl) {        
        this.impl = impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.awt.AWTContactList getImpl() {        
        return impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param list 
 */
    public void onListChange(im.model.ContactList list) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param uid 
 */
    public void onUserIdChange(String uid) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param name 
 */
    public void onNameChange(String name) {        
        int index = getListView().getContactViews().indexOf(this);
        if (index > -1) {
            getImpl().removeContact(index);
            getImpl().addContact(getModel().getName(), index);
        }
    } 
 }
