
package im;
import im.ExceptionReporter;

/**
 * <p></p>
 */
public class InstantMessagingClient extends javax.microedition.midlet.MIDlet implements observer.Observer, im.ExceptionReporter {

/**
 * <p>Represents ...</p>
 */
    private boolean initialized = false;

/**
 * <p>Represents ...</p>
 */
    private static im.InstantMessagingClient instance;

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector conversation = new java.util.Vector();

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector network = new java.util.Vector();

/**
 * <p>Represents ...</p>
 */
    private im.model.ContactList contactList = new im.model.ContactList();

/**
 * <p>Represents ...</p>
 */
    private im.view.ViewFactory viewFactory = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param name 
 * @return 
 */
    public String getParameter(String name) {        
        return getAppProperty(name);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void startApp() {        
        if (!initialized) {
            init();
        }
        start();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void pauseApp() {        
        stop();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param unconditional 
 */
    public void destroyApp(boolean unconditional) {        
        stop();
        destroy();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void destroy() {        
        // your code here
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
 * @param name 
 * @param value 
 */
    public void update(String name, Object value) {        
        if (name.equals("RecvMsg")) {
            onRecvMsgChange((im.model.Message) value);
        }
        if (name.equals("RecvContact")) {
            onRecvContactChange((im.model.Contact) value);
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
    public java.util.Vector getNetworks() {        
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
    public java.util.Vector getConversations() {        
        return conversation;
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
            return (im.networking.Network) network.elementAt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param index 
 * @return 
 */
    public im.model.Conversation getConversationAt(int index) {        
        try {
            return (im.model.Conversation) conversation.elementAt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
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
        this.network.addElement(network);
        // End original body
        if (network != null) network.addObserver(this);
        // End subscribe stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param conversation 
 */
    public void addConversation(im.model.Conversation conversation) {        
        this.conversation.addElement(conversation);
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
            this.network.insertElementAt(network, index);} catch (ArrayIndexOutOfBoundsException e) {
            this.network.addElement(network);
        };
        // End original body
        if (network != null) network.addObserver(this);
        // End subscribe stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param conversation 
 * @param index 
 */
    public void insertConversation(im.model.Conversation conversation, int index) {        
        try {
            this.conversation.insertElementAt(conversation, index);} catch (ArrayIndexOutOfBoundsException e) {
            this.conversation.addElement(conversation);
        };
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
        this.network.removeElement(network);
        // End original body
        // End subscribe stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param conversation 
 */
    public void removeConversation(im.model.Conversation conversation) {        
        this.conversation.removeElement(conversation);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void init() {        
        instance = this;
                loadSettings();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void start() {        
        for (int i = 0; i < getNetworks().size(); i++) {
            im.model.Identity id = getContactList().getIdentity(getNetworkAt(i));
            if (id != null) {
                getNetworkAt(i).login(id.getUserId(), id.getPassword());
            }
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void stop() {        
        for (int i = 0; i < getNetworks().size(); i++) {
            if (getContactList().getIdentity(getNetworkAt(i)) != null) {
                getNetworkAt(i).logout();
            }
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param r 
 */
    public void onRecvMsgChange(im.model.Message r) {        
        for (int i = 0; i < getConversations().size(); i++) {
            String contact = getConversationAt(i).getContact().getUserId();
            if (contact.equals(r.getSender())) {
                getConversationAt(i).setMessage(r);
                return;
            }
        }
        // create new conversation
        im.model.Conversation conv = new im.model.Conversation();
        new im.edit.ConversationEdit(conv);
        addConversation(conv);
        for (int i = 0; i < getContactList().getContacts().size(); i++) {
            if (getContactList().getContactAt(i).getUserId().equals(r.getSender())) {
                conv.setContact(getContactList().getContactAt(i));
                break;
            }
        }
        if (conv.getContact() == null) {
            conv.setContact(new im.model.Contact());
            conv.getContact().setUserId(r.getSender());
        }
        conv.setMessage(r);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public void onRecvContactChange(im.model.Contact c) {        
        for (int i = 0; i < getContactList().getContacts().size(); i++) {
            im.model.Contact listed = getContactList().getContactAt(i);
            if (listed.getUserId().equals(c.getUserId())) {
                if (c.getStatus() != null) {
                    listed.setStatus(c.getStatus());
                }
                if (c.getName() != null) {
                    listed.setName(c.getName());
                }
                return;
            }
        }
        getContactList().addContact(c);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param e 
 */
    public void report(Exception e) {        
        for (int i = 0; i < getNetworks().size(); i++) {
            if (getNetworkAt(i) instanceof ExceptionReporter) {
                ((ExceptionReporter) getNetworkAt(i)).report(e);
                return;
            }
        }
        System.err.println(e.getMessage());
        e.printStackTrace();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void loadSettings() {        
        try {
            // ViewFactory
            setViewFactory(im.view.ViewFactory.getDefault());
            // Network
            im.networking.Network[] networks = im.networking.Network.getDefault();
            for (int i = 0; i < networks.length; i++) {
                addNetwork(networks[i]);
            }
            // Create and register edit/view
            new im.edit.ContactListEdit(contactList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
 }
