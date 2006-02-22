package im.networking.jabber.defaultjabber;

import java.net.*;

public class DefaultJabber extends im.networking.jabber.Jabber {
private java.lang.String connectError = "";

private java.net.Socket socket = null;

protected void connect(java.lang.String host) throws java.io.IOException {
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

private java.net.Socket connect(java.lang.String host, int port) {
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

public java.net.Socket getSocket() {
return socket;
}

}

