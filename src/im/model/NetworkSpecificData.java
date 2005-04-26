
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
 * @param network 
 */
    public void setNetwork(im.networking.Network network) {        
        // Begin Observable stanza
        if (this.network != network) {
            // Begin original body
        this.network = network;
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Network");
            e.put("class", im.networking.Network.class);
            if (network != null) {
                e.put("value", network);
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
    public im.networking.Network getNetwork() {        
        return network;
    } 
 }
