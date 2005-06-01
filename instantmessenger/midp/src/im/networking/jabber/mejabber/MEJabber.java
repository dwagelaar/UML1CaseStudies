
package im.networking.jabber.mejabber;
import javax.microedition.io.*;

/**
 * <p></p>
 */
public class MEJabber extends im.networking.jabber.Jabber {

/**
 * <p>Represents ...</p>
 */
    private javax.microedition.io.StreamConnection socket = null;

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
    public void setSocket(javax.microedition.io.StreamConnection socket) {        
        // Begin Observable stanza
        if (this.socket != socket) {
            // Begin original body
        this.socket = socket;
            // End original body
            notifyObservers("Socket", socket);
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
    public javax.microedition.io.StreamConnection getSocket() {        
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
            getSocket().openInputStream(), getSocket().openOutputStream()));
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
    private javax.microedition.io.StreamConnection connect(String host, int port) {        
        StreamConnection s = null;
        try {
            s = (StreamConnection) Connector.open("socket://" + host + ":" + port);
        } catch (Exception e) {
            connectError = e.getMessage();
        }
        return s;
    } 
 }
