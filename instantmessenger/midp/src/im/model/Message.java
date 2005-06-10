
package im.model;

/**
 * <p></p>
 */
public class Message extends im.model.NetworkSpecificData {

/**
 * <p>Represents ...</p>
 */
    private im.model.Conversation conversation = null;

/**
 * <p>Represents ...</p>
 */
    private String sender;

/**
 * <p>Represents ...</p>
 */
    private String recipient;

/**
 * <p>Represents ...</p>
 */
    private Object content;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param conversation 
 */
    public void setConversation(im.model.Conversation conversation) {        
        // Begin Observable stanza
        if (this.conversation != conversation) {
            // Begin original body
        if (this.conversation != conversation) {
            this.conversation = conversation;
            conversation.setMessage(this);
        }
            // End original body
            notifyObservers("Conversation", conversation);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param recipient 
 */
    public void setRecipient(String recipient) {        
        // Begin Observable stanza
        if (this.recipient != recipient) {
            // Begin original body
        this.recipient = recipient;
            // End original body
            notifyObservers("Recipient", recipient);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param content 
 */
    public void setContent(Object content) {        
        // Begin Observable stanza
        if (this.content != content) {
            // Begin original body
        this.content = content;
            // End original body
            notifyObservers("Content", content);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param sender 
 */
    public void setSender(String sender) {        
        // Begin Observable stanza
        if (this.sender != sender) {
            // Begin original body
        this.sender = sender;
            // End original body
            notifyObservers("Sender", sender);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.Conversation getConversation() {        
        return conversation;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getRecipient() {        
        return recipient;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public Object getContent() {        
        return content;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getSender() {        
        return sender;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void send() {        
        if (getNetwork() != null) {
            getNetwork().send(this);
        }
    } 
 }
