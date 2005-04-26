
package im;

/**
 * <p></p>
 */
public class InstantMessagingClient implements java.util.Observer {

/**
 * <p>Represents ...</p>
 */
    private static im.InstantMessagingClient instance;

/**
 * <p>Represents ...</p>
 */
    private java.util.Collection messageFactory = new java.util.ArrayList();

/**
 * <p>Represents ...</p>
 */
    private im.model.ContactList contactList = new im.model.ContactList();

/**
 * <p>Represents ...</p>
 */
    private im.view.ViewFactory viewFactory = null;

/**
 * <p>Represents ...</p>
 */
    private java.util.List network = new java.util.ArrayList();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param args 
 */
    public static void main(String[] args) {        
        getInstance();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public static im.InstantMessagingClient getInstance() {        
        if (instance == null) {
            instance = new InstantMessagingClient();
        }
        return instance;
    } 

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
 * @param contactList 
 */
    public void setContactList(im.model.ContactList contactList) {        
        this.contactList = contactList;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param viewFactory 
 */
    public void setViewFactory(im.view.ViewFactory viewFactory) {        
        this.viewFactory = viewFactory;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public java.util.Collection getMessageFactorys() {        
        return messageFactory;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.ContactList getContactList() {        
        return contactList;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public java.util.List getNetworks() {        
        return network;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.ViewFactory getViewFactory() {        
        return viewFactory;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param index 
 * @return 
 */
    public im.networking.Network getNetworkAt(int index) {        
        try {
            return (im.networking.Network) network.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param messageFactory 
 */
    public void addMessageFactory(im.model.MessageFactory messageFactory) {        
        this.messageFactory.add(messageFactory);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param network 
 */
    public void addNetwork(im.networking.Network network) {        
        // Begin subscribe stanza
        // Begin original body
        this.network.add(network);// End original body
        if (network != null) network.addObserver(this);
        // End subscribe stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param network 
 * @param index 
 */
    public void insertNetwork(im.networking.Network network, int index) {        
        // Begin subscribe stanza
        // Begin original body
        try {
            this.network.add(index, network);
        } catch (IndexOutOfBoundsException e) {
            this.network.add(network);
        };// End original body
        if (network != null) network.addObserver(this);
        // End subscribe stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param messageFactory 
 */
    public void removeMessageFactory(im.model.MessageFactory messageFactory) {        
        this.messageFactory.remove(messageFactory);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param network 
 */
    public void removeNetwork(im.networking.Network network) {        
        // Begin subscribe stanza
        if (network != null) network.deleteObserver(this);
        // Begin original body
        this.network.remove(network);// End original body
        // End subscribe stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private  InstantMessagingClient() {        
        viewFactory = new im.view.awt.AWTViewFactory();
        viewFactory.createContactListView(contactList);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onReceivedChange() {        
        // your code here
    } 
 }
