
package im.networking;

/**
 * <p></p>
 */
public abstract class Network extends java.util.Observable {

/**
 * <p>Represents ...</p>
 */
    private im.model.Message received;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param msg 
 */
    public abstract void send(im.model.Message msg);

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param received 
 */
    public void setReceived(im.model.Message received) {        
        this.received = received;
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
 }
