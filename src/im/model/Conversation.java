
package im.model;

/**
 * <p></p>
 */
public class Conversation extends java.util.Observable {

/**
 * <p>Represents ...</p>
 */
    private im.model.MessageFactory factory = null;

/**
 * <p>Represents ...</p>
 */
    private im.model.Contact contact = null;

/**
 * <p>Represents ...</p>
 */
    private java.util.List message = new java.util.ArrayList();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param factory 
 */
    public void setFactory(im.model.MessageFactory factory) {        
        // Begin Observable stanza
        if (this.factory != factory) {
            // Begin original body
        this.factory = factory;
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Factory");
            e.put("class", im.model.MessageFactory.class);
            if (factory != null) {
                e.put("value", factory);
            }
            notifyObservers(e);
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
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Contact");
            e.put("class", im.model.Contact.class);
            if (contact != null) {
                e.put("value", contact);
            }
            notifyObservers(e);
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
    public java.util.List getMessages() {        
        return message;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.MessageFactory getFactory() {        
        return factory;
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

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param index 
 * @return 
 */
    public im.model.Message getMessageAt(int index) {        
        try {
            return (im.model.Message) message.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param message 
 */
    public void addMessage(im.model.Message message) {        
        // Begin Observable stanza
        if (! this.message.contains(message)) {
            // Begin original body
        if (! this.message.contains(message)) {
            this.message.add(message);
            message.setConversation(this);
        }
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Message");
            e.put("class", im.model.Message.class);
            if (message != null) {
                e.put("value", message);
            }
            notifyObservers(e);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param message 
 * @param index 
 */
    public void insertMessage(im.model.Message message, int index) {        
        // Begin Observable stanza
        if (! this.message.contains(message)) {
            // Begin original body
        if (! this.message.contains(message)) {
            try {
            this.message.add(index, message);
        } catch (IndexOutOfBoundsException e) {
            this.message.add(message);
        };
            message.setConversation(this);
        }
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Message");
            e.put("class", im.model.Message.class);
            if (message != null) {
                e.put("value", message);
            }
            notifyObservers(e);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param message 
 */
    public void removeMessage(im.model.Message message) {        
        // Begin Observable stanza
        if (this.message.contains(message)) {
            // Begin original body
        if (this.message.contains(message)) {
            this.message.remove(message);
            message.setConversation(null);
        }
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Message");
            e.put("class", im.model.Message.class);
            if (message != null) {
                e.put("value", message);
            }
            notifyObservers(e);
        }
        // End Observable stanza
    } 
 }
