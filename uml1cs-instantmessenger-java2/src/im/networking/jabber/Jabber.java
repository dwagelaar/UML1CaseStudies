package im.networking.jabber;

import com.jabberwookie.ns.jabber.*;
import com.jabberwookie.ns.jabber.iq.*;
import com.jabberwookie.*;

public abstract class Jabber extends im.networking.Network implements com.jabberwookie.MessageListener, com.jabberwookie.PresenceListener, com.jabberwookie.IQListener {
public class ConnectedState extends State {
public void removeContact(final im.model.Contact c) {
        if (!getConnection().isConnected()) {
            Jabber.this.state = new DisconnectedState();
            return;
        }
        Thread removeContact = new Thread() {
            public void run() {
                try {
                    IQRoster roster = new IQRoster();
                    roster.addItem(c.getUserId());
                    ((IQRoster.Item) roster.getChild(0)).setSubscription(Const.REMOVE);
                    IQ iq = new IQ(Const.SET);
                    iq.addChild(roster);
                    getConnection().send(iq);
                    Presence presence = new Presence(c.getUserId(), Const.UNSUBSCRIBE);
                    System.out.println("Removing contact " + presence);
                    getConnection().send(presence);
                } catch (java.io.IOException e) {
                    im.InstantMessagingClient.getInstance().report(e);
                }
            }
        };
        removeContact.start();

}

public void logout() {
Thread t = new Thread() {
    public void run() {
        try {
            System.out.println("Closing connection for " + getConnection().getServerName());
            Presence p = new Presence();
            p.setType(Const.UNAVAILABLE);
            getConnection().send(p);
            getConnection().close();
            synchronized(Jabber.this) {
                Jabber.this.state = new DisconnectedState();
            }
        } catch (java.io.IOException e) {
            im.InstantMessagingClient.getInstance().report(e);
        }

    }
};
t.start();
}

public void send(final im.model.Message msg) {
        if (!getConnection().isConnected()) {
            Jabber.this.state = new DisconnectedState();
            return;
        }
        Thread send = new Thread() {
            public void run() {
                try {
                    Message message = new Message();
                    message.setTo(msg.getRecipient());
                    message.setFrom(msg.getSender());
                    message.setBody(msg.getContent().toString());
                    System.out.println("Sending " + msg + ": " + message);
                    getConnection().send(message);
                } catch (java.io.IOException e) {
                    im.InstantMessagingClient.getInstance().report(e);
                }
            }
        };
        send.start();

}

public void addContact(final im.model.Contact c) {
        if (!getConnection().isConnected()) {
            Jabber.this.state = new DisconnectedState();
            return;
        }
        Thread addContact = new Thread() {
            public void run() {
                try {
                    Presence presence = new Presence(c.getUserId(), Const.SUBSCRIBE);
                    System.out.println("Adding contact " + presence);
                    getConnection().send(presence);
                    IQRoster roster = new IQRoster();
                    roster.addItem(c.getUserId(), c.getName());
                    IQ iq = new IQ(Const.SET);
                    iq.addChild(roster);
                    getConnection().send(iq);
                } catch (java.io.IOException e) {
                    im.InstantMessagingClient.getInstance().report(e);
                }
            }
        };
        addContact.start();

}

}

public class DisconnectedState extends State {
public void login(final java.lang.String uid, final java.lang.String pwd) {
Thread t = new Thread() {
    public void run() {
        try {
            java.util.Vector address = com.ssttr.util.Strings.tokenize(uid, '@');
            String user = (String) address.elementAt(0);
            String server = (String) address.elementAt(1);
            address = com.ssttr.util.Strings.tokenize(server, '/');
            server = (String) address.elementAt(0);
            String resource = (String) address.elementAt(1);
            connect(server);
            getConnection().setMessageListener(Jabber.this);
            getConnection().setIQListener(Jabber.this);
            getConnection().setPresenceListener(Jabber.this);
            boolean try_again;
            do {
                try_again = false;
                System.out.println("Logging in " + uid);
                switch (getConnection().loginAny(user, resource,
                        pwd, 30000)) {
                case Client2Server.LOGIN_BAD_PASS:
                    throw new java.io.IOException("Bad password for " + uid);
                case Client2Server.LOGIN_BAD_UID:
                    if (!registerUser(user, pwd))
                        throw new java.io.IOException(
                                "Could not register user for "
                                        + uid);
                    else
                        try_again = true;
                    break;
                case Client2Server.LOGIN_FAILED:
                    throw new java.io.IOException("Login failed: unknown: "
                            + uid);
                case Client2Server.LOGIN_PASS_EXP:
                    System.out
                            .println("Your password has expired for "
                                    + uid);
                    break;
                case Client2Server.LOGIN_OK:
                    break;
                }
            } while (try_again);
            Presence pres = new Presence(Const.AVAILABLE,
                    "Available", 1);
            pres.setFrom(uid);
            getConnection().send(pres);
            incomingPresence(pres);
            getConnection().send(IQRoster.createGetRequest());
            synchronized(Jabber.this) {
                    Jabber.this.state = new ConnectedState();
            }
        } catch (Exception e) {
            im.InstantMessagingClient.getInstance().report(e);
        }

    }
};
t.start();
}

}

public abstract class State {
public void addContact(im.model.Contact c) {

}

public void login(java.lang.String uid, java.lang.String pwd) {

}

public void removeContact(im.model.Contact c) {

}

public void logout() {

}

public void send(im.model.Message msg) {

}

}

private java.lang.String uid = "";

private com.jabberwookie.Client2Server connection;

private State state = new DisconnectedState();

protected abstract void connect(java.lang.String host) throws java.io.IOException;

public void incomingMessage(com.jabberwookie.ns.jabber.Message message) {
        System.out.println("Message: " + message);
        im.model.Message msg = new im.model.Message();
        msg.setSender(stripResource(message.getFrom()));
        msg.setRecipient(message.getTo());
        msg.setContent(message.getBody());
        setRecvMsg(msg);

}

public void incomingIQ(com.jabberwookie.ns.jabber.IQ iq) {
        java.util.Enumeration children = iq.getChildren().elements();
        while (children.hasMoreElements()) {
            com.ssttr.xml.XMLElement el = (com.ssttr.xml.XMLElement) children.nextElement();
            if (el instanceof IQRoster) {
                for (java.util.Enumeration items = el.enumerateChildren(); items.hasMoreElements();) {
                    IQRoster.Item item = (IQRoster.Item) items.nextElement();
                    System.out.println("Roster Item: " + item);
                    if (item.getSubscription().equals(Const.BOTH) || 
                            item.getSubscription().equals(Const.TO)) {
                        im.model.Contact c = new im.model.Contact();
                        c.setNetwork(this);
                        c.setUserId(item.getJID());
                        c.setName(item.getItemName());
                        setRecvContact(c);
                    }
                }
            } else {
                System.out.println("Unknown iq element: " + el);
            }
        }

}

public void login(java.lang.String uid, java.lang.String pwd) {
        this.uid = uid;
        state.login(uid, pwd);

}

public void send(im.model.Message msg) {
        state.send(msg);

}

private boolean registerUser(java.lang.String user, java.lang.String pwd) {
        System.out.println("Registering " + user + " at " + getConnection().getServerName());
        try {
            java.util.Hashtable info = IQRegister.getRequiredRegInfo(getConnection());
            String key, value;
            for (java.util.Enumeration e = info.keys(); e.hasMoreElements();) {
                key = (String) e.nextElement();
                value = (String) info.get(key);
                if (key.equals(Const.USERNAME))
                    info.put(key, user);
                else if (key.equals(Const.PASSWORD))
                    info.put(key, pwd);
                else if (key.equals(Const.EMAIL))
                    info.put(key, user + "@" + getConnection().getServerName());
                else if (key.equals(Const.INSTRUCTIONS))
                    ;
                else
                    System.err.println("Unknown registration parameter: " + key);
            }
            Chunk chunk = getConnection().send(
                IQRegister.createSetRequest(getConnection().getServerName(), info), 30000);
            return (chunk != null && chunk.getType().equals(Const.RESULT));
        } catch (java.io.IOException e) {
            im.InstantMessagingClient.getInstance().report(e);
        }
        return false;

}

private java.lang.String stripResource(java.lang.String jid) {
        if (uid.equals(jid)) {
            return jid;
        } else { // strip resource bit if remote user
            java.util.Vector strip = com.ssttr.util.Strings.tokenize(jid, '/');
            return (String) strip.elementAt(0);
        }

}

public void removeContact(im.model.Contact c) {
        state.removeContact(c);

}

public void incomingPresence(com.jabberwookie.ns.jabber.Presence presence) {
        String type = presence.getType();
        if (type == null) type = "";
        if (type.equals(Const.SUBSCRIBE)) {
            // someone wants to subscribe to our presence
            // so we slightly re-write the packet to send back 
            presence.setType(Const.SUBSCRIBED);
            System.out.println(presence.getFrom() + " wants to subscribe to your presence, allowing.");
        } else if (type.equals(Const.UNSUBSCRIBE)) {
            // someone wants to unsubscribe from our presence
            // so we slightly re-write the packet to send back 
            presence.setType(Const.UNSUBSCRIBED);
            System.out.println(presence.getFrom() + " wants to unsubscribe from your presence, allowing.");
        } else if (type.equals(Const.SUBSCRIBED)) {
            System.out.println("Subscribed to: " + presence);
        } else if (type.equals(Const.UNSUBSCRIBED)) {
            System.out.println("Unsubscribed from: " + presence);
        } else if (type.equals(Const.UNAVAILABLE)) {
            im.model.Contact c = new im.model.Contact();
            c.setNetwork(this);
            c.setUserId(stripResource(presence.getFrom()));
            c.setStatus("offline");
            setRecvContact(c);
            return;
        } else {
            System.out.println("Received presence: " + presence);
            im.model.Contact c = new im.model.Contact();
            c.setNetwork(this);
            c.setUserId(stripResource(presence.getFrom()));
            if (presence.getShow() != null)
            	c.setStatus(presence.getShow());
            else
            	c.setStatus(Const.AVAILABLE);
            setRecvContact(c);
            return;
        }
        // make sure to return it to the sender and not ourselves
        String from = presence.getTo();
        presence.setTo(presence.getFrom());
        presence.setFrom(from);
        try {
            getConnection().send(presence);
        } catch (java.io.IOException e) {
            im.InstantMessagingClient.getInstance().report(e);
        }

}

public Jabber() {
        setName("Jabber");

}

public void logout() {
        state.logout();

}

public void addContact(im.model.Contact c) {
        state.addContact(c);

}

public void setConnection(com.jabberwookie.Client2Server connection) {
// Begin Observable stanza
if (this.connection != connection) {
    // Begin original body
this.connection = connection;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "Connection");
    e.put("class", com.jabberwookie.Client2Server.class);
    if (connection != null) {
        e.put("value", connection);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public com.jabberwookie.Client2Server getConnection() {
return connection;
}

}

