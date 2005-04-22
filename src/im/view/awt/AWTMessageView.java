
package im.view.awt;

/**
 * <p></p>
 */
public class AWTMessageView extends im.view.MessageView {

/**
 * <p>Represents ...</p>
 */
    private im.view.awt.AWTMessage impl = new AWTMessage();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param impl 
 */
    public void setImpl(im.view.awt.AWTMessage impl) {        
        this.impl = impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.awt.AWTMessage getImpl() {        
        return impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param sender 
 */
    public void onSenderChange(String sender) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param recipient 
 */
    public void onRecipientChange(String recipient) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param content 
 */
    public void onContentChange(Object content) {        
        // your code here
    } 
 }
