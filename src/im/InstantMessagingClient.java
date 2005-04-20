
package im;

/**
 * <p></p>
 */
public class InstantMessagingClient implements java.util.Observer {

/**
 * <p>Represents ...</p>
 */
    private java.util.Collection network = new java.util.ArrayList();

/**
 * <p>Represents ...</p>
 */
    private im.model.ContactList contactList = new im.model.ContactList();

/**
 * <p>Represents ...</p>
 */
    private java.util.Collection messageFactory = new java.util.ArrayList();

/**
 * <p>Represents ...</p>
 */
    private im.view.ViewFactory viewFactory = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param args 
 */
    public static void main(String[] args) {        
        new InstantMessagingClient();
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
 * @return 
 */
    public java.util.Collection getNetworks() {        
        return network;
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
    public java.util.Collection getMessageFactorys() {        
        return messageFactory;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param network 
 */
    public void addNetwork(im.networking.Network network) {        
        this.network.add(network);
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
    public void removeNetwork(im.networking.Network network) {        
        this.network.remove(network);
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
 */
    public  InstantMessagingClient() {        
        // your code here
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
