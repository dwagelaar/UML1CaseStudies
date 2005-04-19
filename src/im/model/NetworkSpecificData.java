
package im.model;

/**
 * <p></p>
 */
public abstract class NetworkSpecificData extends java.util.Observable {

/**
 * <p>Represents ...</p>
 */
    private im.networking.Network network = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.networking.Network getNetwork() {        
        return network;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param network 
 */
    public void setNetwork(im.networking.Network network) {        
        this.network = network;
    } 
 }
