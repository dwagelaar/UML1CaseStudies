
package im.networking;

/**
 * <p></p>
 */
public abstract class Network extends java.util.Observable {

/**
 * <p>Represents ...</p>
 */
    private String name;

/**
 * <p>Represents ...</p>
 */
    private im.model.messages.Message recvMsg;

/**
 * <p>Represents ...</p>
 */
    private im.model.Contact recvContact;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param recvMsg 
 */
    public void setRecvMsg(im.model.messages.Message recvMsg) {        
        // Begin Observable stanza
        if (this.recvMsg != recvMsg) {
            // Begin original body
        this.recvMsg = recvMsg;
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "RecvMsg");
            e.put("class", im.model.messages.Message.class);
            if (recvMsg != null) {
                e.put("value", recvMsg);
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
 * @param name 
 */
    public void setName(String name) {        
        // Begin Observable stanza
        if (this.name != name) {
            // Begin original body
        this.name = name;
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Name");
            e.put("class", String.class);
            if (name != null) {
                e.put("value", name);
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
 * @param recvContact 
 */
    public void setRecvContact(im.model.Contact recvContact) {        
        // Begin Observable stanza
        if (this.recvContact != recvContact) {
            // Begin original body
        this.recvContact = recvContact;
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "RecvContact");
            e.put("class", im.model.Contact.class);
            if (recvContact != null) {
                e.put("value", recvContact);
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
    public im.model.messages.Message getRecvMsg() {        
        return recvMsg;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getName() {        
        return name;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.Contact getRecvContact() {        
        return recvContact;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param msg 
 */
    public abstract void send(im.model.messages.Message msg);

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param uid 
 * @param pwd 
 * @return 
 */
    public boolean login(String uid, String pwd) {        
        // your code here
        return true;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void logout() {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public void addContact(im.model.Contact c) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public void removeContact(im.model.Contact c) {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public static im.networking.Network[] getDefault() {        
        String[] options = {
            "im.networking.jabber.Jabber",
            "im.networking.sms.SMS"
        };
        java.util.Vector networks = new java.util.Vector();
        for (int i = 0; i < options.length; i++) {
            try {
                networks.addElement(Class.forName(options[i]).newInstance());
            } catch (Exception e) {
            }
        }
        Network[] nwArray = new Network[networks.size()];
        networks.copyInto(nwArray);
        return nwArray;
    } 
 }
