
package im.model;

/**
 * <p></p>
 */
public class Conversation {

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector observers = new java.util.Vector();

/**
 * <p>Represents ...</p>
 */
    private im.model.Contact contact = null;

/**
 * <p>Represents ...</p>
 */
    private im.model.Message message = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param observer 
 */
    public void addObserver(observer.Observer observer) {        
        observers.addElement(observer);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param observer 
 */
    public void deleteObserver(observer.Observer observer) {        
        observers.removeElement(observer);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param name 
 * @param value 
 */
    protected void notifyObservers(String name, Object value) {        
        for (int i = 0; i < observers.size(); i++) {
            ((observer.Observer) observers.elementAt(i)).update(name, value);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param message 
 */
    public void setMessage(im.model.Message message) {        
        // Begin Observable stanza
        if (this.message != message) {
            // Begin original body
        if (this.message != message) {
            this.message = message;
            message.setConversation(this);
        }
            // End original body
            notifyObservers("Message", message);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 */
    public void setContact(im.model.Contact contact) {        
        // Begin Observable stanza
        if (this.contact != contact) {
            // Begin original body
        this.contact = contact;
            // End original body
            notifyObservers("Contact", contact);
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
    public im.model.Message getMessage() {        
        return message;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.Contact getContact() {        
        return contact;
    } 
 }
