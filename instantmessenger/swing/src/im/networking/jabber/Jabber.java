
package im.networking.jabber;
import java.io.IOException;
import java.util.Vector;

import com.jabberwookie.*;
import com.jabberwookie.ns.jabber.*;
import com.jabberwookie.ns.jabber.iq.*;
<<<<<<< .mine
import com.ssttr.util.Strings;
//import java.net.*;
=======
import com.ssttr.util.Strings;

import java.net.*;
>>>>>>> .r1281
import java.util.Vector;

/**
 * <p></p>
 */
public abstract class Jabber extends im.networking.Network implements com.jabberwookie.IQListener, com.jabberwookie.MessageListener, com.jabberwookie.PresenceListener {

    abstract class State {
        public void login(String uid, String pwd) {}
        public void logout() {}
        public void addContact(im.model.Contact c) {}
        public void removeContact(im.model.Contact c) {}
        public void send(im.model.Message msg) {}
    }
    
    class DisconnectedState extends State {
        public void login(final String uid, final String pwd) {
			Thread login = new Thread() {
				public void run() {
					try {
						java.util.Vector address = Strings.tokenize(uid, '@');
						String user = (String) address.elementAt(0);
						String server = (String) address.elementAt(1);
						address = Strings.tokenize(server, '/');
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
								throw new IOException("Bad password for " + uid);
							case Client2Server.LOGIN_BAD_UID:
								if (!registerUser(user, pwd))
									throw new IOException(
											"Could not register user for "
													+ uid);
								else
									try_again = true;
								break;
							case Client2Server.LOGIN_FAILED:
								throw new IOException("Login failed: unknown: "
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
					} catch (IOException e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
					}
				}
			};
            login.start();
        }
    }
    
    class ConnectedState extends State {
        public void logout() {
            Thread logout = new Thread() {
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
                        e.printStackTrace();
                    }
                }
            };
            logout.start();
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
            			System.err.println(e.getMessage());
            			e.printStackTrace();
            		}
            	}
            };
            addContact.start();
		}

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
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                    }
                }
            };
            removeContact.start();
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
                        System.err.println(e.getMessage());
                    }
                }
            };
            send.start();
		}
    }
    
    private State state = new DisconnectedState();
    
/**
 * <p>
 * Represents ...
 * </p>
 */
    //private java.net.Socket socket = null;

/**
 * <p>
 * Represents ...
 * </p>
 */
    private com.jabberwookie.Client2Server connection;

/**
 * <p>
 * Represents ...
 * </p>
 */
//    private String connectError = "";

/**
 * <p>
 * Represents ...
 * </p>
 */
    private String uid = "";

/**
 * <p>
 * Does ...
 * </p>
 * 
 * 
 * 
 * @param connection
 */
    public void setConnection(com.jabberwookie.Client2Server connection) {        
        // Begin Observable stanza
        if (this.connection != connection) {
            // Begin original body
        this.connection = connection;
            // End original body
            notifyObservers("Connection", connection);
        }
        // End Observable stanza
    } 

/**
 * <p>
 * Does ...
 * </p>
 * 
 * 
 * 
 * @return
 */
    public com.jabberwookie.Client2Server getConnection() {        
        return connection;
    } 

/**
 * <p>
 * Does ...
 * </p>
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
        state.send(msg);
    } 

    abstract protected void connect(String host) throws java.io.IOException;
    
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param uid 
 * @param pwd 
 * @return 
 */
<<<<<<< .mine
    public void login(String uid, String pwd) {        
        this.uid = uid;
        state.login(uid, pwd);
=======
    public boolean login(String uid, String pwd) {        
        try {
            this.uid = uid; 
            //TODO: change in model
            java.util.Vector address = Strings.tokenize(uid, '@');
            String user = (String) address.elementAt(0);
            String server = (String) address.elementAt(1);
            address = Strings.tokenize(server, '/');
            server = (String) address.elementAt(0);
            String resource = (String) address.elementAt(1);
//            java.util.StringTokenizer address = new java.util.StringTokenizer(uid, "@");
//            String user = address.nextToken();
//            String server = address.nextToken();
//            address = new java.util.StringTokenizer(server, "/");
//            server = address.nextToken();
//            String resource = address.nextToken();
            setSocket(connect(server, 5222));
            if (getSocket() == null) {
                throw new SocketException(connectError);
            }
            setConnection(new Client2Server(getSocket().getInputStream(), getSocket().getOutputStream()));
            if (!getConnection().open(server, 60000)) {
                throw new ConnectException("Could not establish stream to " + server + ":5222 after 60 seconds");
            }
            getConnection().setMessageListener(this);
            getConnection().setIQListener(this);
            getConnection().setPresenceListener(this);
            boolean try_again;
            do {
                try_again = false;
                System.out.println("Logging in " + uid);
                switch (getConnection().loginAny(user, resource, pwd, 30000)) {
                    case Client2Server.LOGIN_BAD_PASS:
                        throw new ConnectException("Bad password for " + uid);
                    case Client2Server.LOGIN_BAD_UID:
                        if (!registerUser(user, pwd))
                            throw new ConnectException("Could not register user for " + uid);
                        else
                            try_again = true;
                        break;
                    case Client2Server.LOGIN_FAILED:
                        throw new ConnectException("Login failed: unknown: " + uid);
                    case Client2Server.LOGIN_PASS_EXP:
                        System.out.println("Your password has expired for " + uid);
                        break;
                    case Client2Server.LOGIN_OK:
                        break;
                }
            } while (try_again);
            Presence pres = new Presence(Const.AVAILABLE, "Available", 1);
            pres.setFrom(uid);
            getConnection().send(pres);
            incomingPresence(pres);
            getConnection().send(IQRoster.createGetRequest());
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
>>>>>>> .r1281
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void logout() {
        state.logout();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public void addContact(im.model.Contact c) {        
        state.addContact(c);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public void removeContact(im.model.Contact c) {        
        state.removeContact(c);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param message 
 */
    public void incomingMessage(com.jabberwookie.ns.jabber.Message message) {        
        System.out.println("Message: " + message);
        im.model.Message msg = new im.model.Message();
        msg.setSender(stripResource(message.getFrom()));
        msg.setRecipient(message.getTo());
        msg.setContent(message.getBody());
        setRecvMsg(msg);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param iq 
 */
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

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param presence 
 */
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
            e.printStackTrace();
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param user 
 * @param pwd 
 * @return 
 */
    private boolean registerUser(String user, String pwd) {        
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
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
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
//    private java.net.Socket connect(String host, int port) {        
//        Socket s = null;
//        try {
//            InetAddress[] hosts = InetAddress.getAllByName(host);
//            java.util.Stack exceptions = new java.util.Stack();
//            for (int i = 0; i < hosts.length; i++) {
//                try {
//                    s = new Socket(hosts[i], port);
//                    break;
//                } catch (Exception e) {
//                    exceptions.push(e);
//                }
//            }
//            StringBuffer sb = new StringBuffer();
//            while (!exceptions.empty()) {
//                sb.append(((Exception) exceptions.pop()).getMessage());
//            }
//            connectError = sb.toString();
//        } catch (Exception e) {
//            connectError = e.getMessage();
//        }
//        return s;
//    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param jid 
 * @return 
 */
    private String stripResource(String jid) {        
        if (uid.equals(jid)) {
        	return jid;
        } else { // strip resource bit if remote user
<<<<<<< .mine
            Vector strip = Strings.tokenize(jid, '/');
            return (String) strip.elementAt(0);
//        	java.util.StringTokenizer strip =
//        		new java.util.StringTokenizer(jid, "/");
//        	return strip.nextToken();
=======
            //TODO: change in model
            Vector strip = Strings.tokenize(jid, '/');
            return (String) strip.elementAt(0);
//          java.util.StringTokenizer strip =
//              new java.util.StringTokenizer(jid, "/");
//          return strip.nextToken();
>>>>>>> .r1281
        }
    } 
 }
