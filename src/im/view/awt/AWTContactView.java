
package im.view.awt;

/**
 * <p></p>
 */
public class AWTContactView extends im.view.ContactView {

/**
 * <p>Represents ...</p>
 */
    private im.view.awt.AWTContactList window = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param window 
 */
    public void setWindow(im.view.awt.AWTContactList window) {        
        this.window = window;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.awt.AWTContactList getWindow() {        
        return window;
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
        // your code here
    } 
 }
