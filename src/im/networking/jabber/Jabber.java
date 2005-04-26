
package im.networking.jabber;

/**
 * <p></p>
 */
public class Jabber extends im.networking.Network implements java.util.Observer {

/**
 * <p>Represents ...</p>
 */
    private im.networking.jabber.Socket socket = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param o 
 * @param arg 
 */
    public void update(java.util.Observable o, Object arg) {        
        if (arg instanceof java.util.Hashtable) {
            java.util.Hashtable e = (java.util.Hashtable) arg;
            String mName = "on" + ((String) e.get("name")) + "Change";
            Class[] parmTypes = { (Class) e.get("class") };
            try {
                java.lang.reflect.Method m = getClass().getDeclaredMethod(mName, parmTypes);
                Object[] args = { e.get("value") };
                m.invoke(this, args);
            } catch (NoSuchMethodException nex) {
                // no handler
            } catch (Exception ex) {
                // wrong handler configuration
                ex.printStackTrace();
            }
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param socket 
 */
    public void setSocket(im.networking.jabber.Socket socket) {        
        // Begin Observable stanza
        if (this.socket != socket) {
            // Begin original body
        // Begin subscribe stanza
        if (this.socket != null) this.socket.deleteObserver(this);
        // Begin original body
        this.socket = socket;// End original body
        if (socket != null) socket.addObserver(this);
        // End subscribe stanza
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Socket");
            e.put("class", im.networking.jabber.Socket.class);
            if (socket != null) {
                e.put("value", socket);
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
    public im.networking.jabber.Socket getSocket() {        
        return socket;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  Jabber() {        
        setName("Jabber");
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param msg 
 */
    public void send(im.model.Message msg) {        
        getSocket().sendData(msg);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param msg 
 */
    public void onReceivedChange(im.model.Message msg) {        
        setReceived(msg);
    } 
 }
