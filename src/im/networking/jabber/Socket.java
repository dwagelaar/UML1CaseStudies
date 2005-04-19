
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
        this.received = received;
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
 }
