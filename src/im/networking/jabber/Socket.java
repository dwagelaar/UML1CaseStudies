
package im.networking.jabber;

/**
 * <p></p>
 */
public class Socket extends java.util.Observable {

/**
 * <p>Represents ...</p>
 */
    private Object received;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param received 
 */
    public void setReceived(Object received) {        
        // Begin Observable stanza
        if (this.received != received) {
            // Begin original body
        this.received = received;
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Received");
            e.put("class", Object.class);
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
 * @return 
 */
    public Object getReceived() {        
        return received;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param data 
 */
    public void sendData(Object data) {        
        // your code here
    } 
 }
