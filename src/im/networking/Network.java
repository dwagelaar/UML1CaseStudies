
package im.networking;

/**
 * <p></p>
 */
public abstract class Network extends java.util.Observable {

/**
 * <p>Represents ...</p>
 */
    private String name;

/**
 * <p>Represents ...</p>
 */
    private im.model.Message received;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param received 
 */
    public void setReceived(im.model.Message received) {        
        // Begin Observable stanza
        if (this.received != received) {
            // Begin original body
        this.received = received;
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Received");
            e.put("class", im.model.Message.class);
            if (received != null) {
                e.put("value", received);
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
 * @param name 
 */
    public void setName(String name) {        
        // Begin Observable stanza
        if (this.name != name) {
            // Begin original body
        this.name = name;
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Name");
            e.put("class", String.class);
            if (name != null) {
                e.put("value", name);
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
    public im.model.Message getReceived() {        
        return received;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getName() {        
        return name;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param msg 
 */
    public abstract void send(im.model.Message msg);
 }
