
package im.view.awt;

/**
 * <p></p>
 */
public class AWTConversationView extends im.view.ConversationView {

/**
 * <p>Represents ...</p>
 */
    private im.view.awt.AWTConversation impl = new AWTConversation();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param impl 
 */
    public void setImpl(im.view.awt.AWTConversation impl) {        
        this.impl = impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.awt.AWTConversation getImpl() {        
        return impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  AWTConversationView() {        
        getImpl().addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                onWindowClosing();
            }
        });
        getImpl().addSendActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onSendClicked();
            }
        });
        getImpl().setVisible(true);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param m 
 */
    public void onMessageChange(im.model.Message m) {        
        if (getModel().getMessages().contains(m)) {
            if (m.getContent() instanceof String) {
                im.model.ContactList list =
                    im.InstantMessagingClient.getInstance().getContactList();
                getImpl().addContent(list.getUserName(m.getSender())
                    + ": " + m.getContent());
            } else {
                getImpl().addContent(m.getContent());
            }
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public void onContactChange(im.model.Contact c) {        
        getImpl().setTitle(getModel().getContact().getName());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param f 
 */
    public void onFactoryChange(im.model.MessageFactory f) {        
        if (f.createMessage().getContent() instanceof String) {
            getImpl().enableText();
        } else {
            getImpl().disableText();
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onSendClicked() {        
        im.model.Contact recipient = getModel().getContact();
        im.model.Contact sender = getSender(recipient);
        im.model.Message msg = getModel().getFactory().createMessage();
        msg.setNetwork(recipient.getNetwork());
        msg.setSender(sender.getUserId());
        msg.setRecipient(recipient.getUserId());
        msg.setContent(getImpl().getContent());
        msg.send();
        getImpl().setContent(null);
        getModel().addMessage(msg);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onWindowClosing() {        
        getImpl().setVisible(false);
        im.InstantMessagingClient.getInstance().removeConversation(getModel());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param recipient 
 * @return 
 */
    public im.model.Contact getSender(im.model.Contact recipient) {        
        im.InstantMessagingClient c = im.InstantMessagingClient.getInstance();
        return c.getContactList().getIdentity(recipient.getNetwork());
    } 
 }
