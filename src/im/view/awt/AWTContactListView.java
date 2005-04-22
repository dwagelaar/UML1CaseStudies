
package im.view.awt;

/**
 * <p></p>
 */
public class AWTContactListView extends im.view.ContactListView implements java.awt.event.WindowListener {

/**
 * <p>Represents ...</p>
 */
    private im.view.awt.AWTContactList impl = new AWTContactList();

/**
 * <p>Represents ...</p>
 */
    private im.view.awt.AWTViewFactory factory = null;

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector contactCache = new java.util.Vector();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param factory 
 */
    public void setFactory(im.view.awt.AWTViewFactory factory) {        
        this.factory = factory;
    } 

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
    public im.view.awt.AWTViewFactory getFactory() {        
        return factory;
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
 */
    public  AWTContactListView() {        
        getImpl().addWindowListener(this);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 */
    public void onContactChange(im.model.Contact contact) {        
        int index;
        if (getModel().getContacts().contains(contact)) {
            index = getModel().getContacts().indexOf(contact);
            im.view.ContactView view = getFactory().createContactView(contact);
            getImpl().addContact(contact.getName(), index);
            try {
                contactCache.insertElementAt(contact, index);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } else {
            index = contactCache.indexOf(contact);
            getImpl().removeContact(index);
            try {
                contactCache.removeElementAt(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param e 
 */
    public void windowActivated(java.awt.event.WindowEvent e) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param e 
 */
    public void windowClosed(java.awt.event.WindowEvent e) {        
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param e 
 */
    public void windowClosing(java.awt.event.WindowEvent e) {        
        System.exit(0);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param e 
 */
    public void windowDeactivated(java.awt.event.WindowEvent e) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param e 
 */
    public void windowDeiconified(java.awt.event.WindowEvent e) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param e 
 */
    public void windowIconified(java.awt.event.WindowEvent e) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param e 
 */
    public void windowOpened(java.awt.event.WindowEvent e) {        
        // your code here
    } 
 }
