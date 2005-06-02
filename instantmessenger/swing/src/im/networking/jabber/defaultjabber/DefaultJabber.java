
package im.networking.jabber.defaultjabber;
import java.net.*;

/**
 * <p></p>
 */
public class DefaultJabber extends im.networking.jabber.Jabber {

/**
 * <p>Represents ...</p>
 */
    private java.net.Socket socket = null;

/**
 * <p>Represents ...</p>
 */
    private String connectError = "";

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param socket 
 */
    public void setSocket(java.net.Socket socket) {        
        // Begin Observable stanza
        if (this.socket != socket) {
            // Begin original body
        this.socket = socket;
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Socket");
            e.put("class", java.net.Socket.class);
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
    public java.net.Socket getSocket() {        
        return socket;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * @throws java.io.IOException 
 * 
 * @param host 
 */
    protected void connect(String host) throws java.io.IOException {        
        setSocket(connect(host, 5222));
        if (getSocket() == null) {
            throw new java.io.IOException(connectError);
        }
        setConnection(new com.jabberwookie.Client2Server(
            getSocket().getInputStream(), getSocket().getOutputStream()));
        if (!getConnection().open(host, 60000)) {
            throw new java.io.IOException("Could not establish stream to " + host + ":5222 after 60 seconds");
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param host 
 * @param port 
 * @return 
 */
    private java.net.Socket connect(String host, int port) {        
        Socket s = null;
        try {
            InetAddress[] hosts = InetAddress.getAllByName(host);
            java.util.Stack exceptions = new java.util.Stack();
            for (int i = 0; i < hosts.length; i++) {
                try {
                    s = new Socket(hosts[i], port);
                    break;
                } catch (Exception e) {
                    exceptions.push(e);
                }
            }
            StringBuffer sb = new StringBuffer();
            while (!exceptions.empty()) {
                sb.append(((Exception) exceptions.pop()).getMessage());
            }
            connectError = sb.toString();
        } catch (Exception e) {
            connectError = e.getMessage() + " at DefaultJabber.connect(" + host + ", " + port + ")";
        }
        return s;
    } 
 }
