
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
 */
    public  AWTMessageView() {        
        getImpl().addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent e) {
                onViewClose();
            }
        });
        getImpl().setVisible(true);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param sender 
 */
    public void onSenderChange(String sender) {        
        onSenderRecipientChange();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param recipient 
 */
    public void onRecipientChange(String recipient) {        
        onSenderRecipientChange();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param content 
 */
    public void onContentChange(Object content) {        
        getImpl().setContent(content);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onSenderRecipientChange() {        
        getImpl().setTitle(getModel().getSender() + " -> " + getModel().getRecipient());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onViewClose() {
        if (getImpl().getSendClicked()) {
        	System.out.println("send clicked; sending message...");
            getModel().setContent(getImpl().getContent());
            getModel().send();
        }
    } 
 }
