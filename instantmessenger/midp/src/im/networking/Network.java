
package im.networking;

/**
 * <p></p>
 */
public abstract class Network {

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector observers = new java.util.Vector();

/**
 * <p>Represents ...</p>
 */
    private String name;

/**
 * <p>Represents ...</p>
 */
    private im.model.Message recvMsg;

/**
 * <p>Represents ...</p>
 */
    private im.model.Contact recvContact;

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
 * @param name 
 * @param value 
 */
    protected void notifyObservers(String name, Object value) {        
        for (int i = 0; i < observers.size(); i++) {
            ((observer.Observer) observers.elementAt(i)).update(name, value);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param recvMsg 
 */
    public void setRecvMsg(im.model.Message recvMsg) {        
        // Begin Observable stanza
        if (this.recvMsg != recvMsg) {
            // Begin original body
        this.recvMsg = recvMsg;
            // End original body
            notifyObservers("RecvMsg", recvMsg);
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
            notifyObservers("RecvContact", recvContact);
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
            notifyObservers("Name", name);
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
    public im.model.Message getRecvMsg() {        
        return recvMsg;
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
 * @param msg 
 */
    public abstract void send(im.model.Message msg);

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param uid 
 * @param pwd 
 */
    public void login(String uid, String pwd) {        
        // your code here
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
            "im.networking.jabber.mejabber.MEJabber",
            "im.networking.jabber.defaultjabber.DefaultJabber",
            "im.networking.sms.SMS",
            "im.networking.local.Local"
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
