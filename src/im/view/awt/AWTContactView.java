
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
 * @param name 
 */
    public void onNameChange(String name) {        
        onChange();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param s 
 */
    public void onStatusChange(String s) {        
        onChange();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onChange() {        
        int index = getListView().getContactViews().indexOf(this);
        if (index > -1) {
            getImpl().removeContact(index);
            getImpl().addContact(getModel(), index);
        }
    } 
 }
