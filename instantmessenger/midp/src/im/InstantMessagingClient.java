
package im;

/**
 * <p></p>
 */
public class InstantMessagingClient extends javax.microedition.midlet.MIDlet implements observer.Observer {

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
    private im.view.ViewFactory viewFactory = null;

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector conversation = new java.util.Vector();

/**
 * <p>Represents ...</p>
 */
    private im.model.ContactList contactList = new im.model.ContactList();

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector messageFactory = new java.util.Vector();

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector network = new java.util.Vector();

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
        destroy();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void start() {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void stop() {        
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
 * @param e 
 */
    public void update(java.util.Hashtable e) {        
        String name = (String) e.get("name");
        if (name.equals("RecvMsg")) {
            onRecvMsgChange((im.model.messages.Message) e.get("value"));
        }
        if (name.equals("RecvContact")) {
            onRecvContactChange((im.model.Contact) e.get("value"));
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
    public java.util.Vector getConversations() {        
        return conversation;
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
    public java.util.Vector getMessageFactorys() {        
        return messageFactory;
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
    public im.model.messages.MessageFactory getMessageFactoryAt(int index) {        
        try {
            return (im.model.messages.MessageFactory) messageFactory.elementAt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
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
 * @param messageFactory 
 */
    public void addMessageFactory(im.model.messages.MessageFactory messageFactory) {        
        this.messageFactory.addElement(messageFactory);
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
 * @param messageFactory 
 * @param index 
 */
    public void insertMessageFactory(im.model.messages.MessageFactory messageFactory, int index) {        
        try {
            this.messageFactory.insertElementAt(messageFactory, index);} catch (ArrayIndexOutOfBoundsException e) {
            this.messageFactory.addElement(messageFactory);
        };
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
 * @param messageFactory 
 */
    public void removeMessageFactory(im.model.messages.MessageFactory messageFactory) {        
        this.messageFactory.removeElement(messageFactory);
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
    public void destroy() {        
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
    public void onRecvMsgChange(im.model.messages.Message r) {        
        for (int i = 0; i < getConversations().size(); i++) {
            String contact = getConversationAt(i).getContact().getUserId();
            im.model.messages.MessageFactory mf = getConversationAt(i).getFactory();
            if (contact.equals(r.getSender()) && (mf == getMessageFactory(r.getContent()))) {
                getConversationAt(i).addMessage(r);
                return;
            }
        }
        // create new conversation
        im.model.Conversation conv = new im.model.Conversation();
        new im.edit.ConversationEdit(conv);
        addConversation(conv);
        for (int i = 0; i < getMessageFactorys().size(); i++) {
            if (getMessageFactoryAt(i).isValidContent(r.getContent())) {
                conv.setFactory(getMessageFactoryAt(i));
                break;
            }
        }
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
        conv.addMessage(r);
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
 * @param c 
 * @return 
 */
    public im.model.messages.MessageFactory getMessageFactory(Object c) {        
        for (int i = 0; i < getMessageFactorys().size(); i++) {
            if (getMessageFactoryAt(i).isValidContent(c)) {
                return getMessageFactoryAt(i);
            }
        }
        return null;
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
            // MessageFactory
            im.model.messages.MessageFactory[] mfs = im.model.messages.MessageFactory.getDefault();
            for (int i = 0; i < mfs.length; i++) {
                addMessageFactory(mfs[i]);
            }
            // Create and register edit/view
            new im.edit.ContactListEdit(contactList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
 }
