
package im.model;

/**
 * <p></p>
 */
public abstract class Message extends im.model.NetworkSpecificData {

/**
 * <p>Represents ...</p>
 */
    private String recipient;

/**
 * <p>Represents ...</p>
 */
    private Object content;

/**
 * <p>Represents ...</p>
 */
    private String sender;

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

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param recipient 
 */
    public void setRecipient(String recipient) {        
        this.recipient = recipient;
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
 * @param sender 
 */
    public void setSender(String sender) {        
        this.sender = sender;
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
 * 
 * @param content 
 */
    public void setContent(Object content) {        
        this.content = content;
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
 }
