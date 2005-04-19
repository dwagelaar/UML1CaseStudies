
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
            Class[] parmTypes = { e.get("value").getClass() };
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
 */
    public void onReceivedChange() {        
        // your code here
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
 * 
 * @param msg 
 */
    public void send(im.model.Message msg) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param socket 
 */
    public void setSocket(im.networking.jabber.Socket socket) {        
        this.socket = socket;
    } 
 }
