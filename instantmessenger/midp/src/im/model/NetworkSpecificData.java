
package im.model;

/**
 * <p></p>
 */
public abstract class NetworkSpecificData {

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector observers = new java.util.Vector();

/**
 * <p>Represents ...</p>
 */
    private im.networking.Network network = null;

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
 * @param e 
 */
    protected void notifyObservers(java.util.Hashtable e) {        
        for (int i = 0; i < observers.size(); i++) {
            ((observer.Observer) observers.elementAt(i)).update(e);
        }
    } 

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
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Network");
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
