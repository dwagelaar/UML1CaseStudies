
package im;

/**
 * <p></p>
 */
public class InstantMessagingClient extends java.applet.Applet implements java.util.Observer {

/**
 * <p>Represents ...</p>
 */
    private static im.InstantMessagingClient instance;

/**
 * <p>Represents ...</p>
 */
    private im.model.ContactList contactList = new im.model.ContactList();

/**
 * <p>Represents ...</p>
 */
    private java.util.List network = new java.util.ArrayList();

/**
 * <p>Represents ...</p>
 */
    private java.util.List conversation = new java.util.ArrayList();

/**
 * <p>Represents ...</p>
 */
    private java.util.List messageFactory = new java.util.ArrayList();

/**
 * <p>Represents ...</p>
 */
    private im.view.ViewFactory viewFactory = null;

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
    public java.util.List getConversations() {        
        return conversation;
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
    public java.util.List getMessageFactorys() {        
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
 * @param index 
 * @return 
 */
    public im.model.Conversation getConversationAt(int index) {        
        try {
            return (im.model.Conversation) conversation.get(index);
        } catch (IndexOutOfBoundsException e) {
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
    public im.model.MessageFactory getMessageFactoryAt(int index) {        
        try {
            return (im.model.MessageFactory) messageFactory.get(index);
        } catch (IndexOutOfBoundsException e) {
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
        this.network.add(network);
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
        this.conversation.add(conversation);
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
 * @param index 
 */
    public void insertNetwork(im.networking.Network network, int index) {        
        // Begin subscribe stanza
        // Begin original body
        try {
            this.network.add(index, network);
        } catch (IndexOutOfBoundsException e) {
            this.network.add(network);
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
            this.conversation.add(index, conversation);
        } catch (IndexOutOfBoundsException e) {
            this.conversation.add(conversation);
        };
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param messageFactory 
 * @param index 
 */
    public void insertMessageFactory(im.model.MessageFactory messageFactory, int index) {        
        try {
            this.messageFactory.add(index, messageFactory);
        } catch (IndexOutOfBoundsException e) {
            this.messageFactory.add(messageFactory);
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
        this.network.remove(network);
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
        this.conversation.remove(conversation);
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
    public void init() {        
        instance = this;
        setLayout(new java.awt.BorderLayout());
                loadSettings();
                try {
                    // login
                    for (int i = 0; i < getNetworks().size(); i++) {
                    	im.model.Identity id = getContactList().getIdentity(getNetworkAt(i));
                        if (id == null) {
                            throw new NullPointerException("Could not find identity for " + getNetworkAt(i).getClass().getName());
                        }
                        id.getNetwork().login(id.getUserId(), id.getPassword());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void destroy() {        
        for (int i = 0; i < getNetworks().size(); i++) {
            getNetworkAt(i).logout();
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
            im.model.MessageFactory mf = getConversationAt(i).getFactory();
            if (contact.equals(r.getSender()) && (mf == getMessageFactory(r.getContent()))) {
                getConversationAt(i).addMessage(r);
                return;
            }
        }
        // create new conversation
        im.model.Conversation conv = new im.model.Conversation();
        getViewFactory().createConversationView(conv);
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
            if (getContactList().getContactAt(i).getUserId().equals(c.getUserId())) {
                getContactList().getContactAt(i).setStatus(c.getStatus());
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
    public im.model.MessageFactory getMessageFactory(Object c) {        
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
            java.util.Properties settings = new java.util.Properties();
            settings.load(InstantMessagingClient.class.getResourceAsStream("InstantMessagingClient.properties"));
            // ViewFactory
            setViewFactory((im.view.ViewFactory) Class.forName(
                settings.getProperty("im.view.ViewFactory")).newInstance());
            // Network
            java.util.StringTokenizer networks = new java.util.StringTokenizer(
                settings.getProperty("im.networking.Network"), ",");
            while (networks.hasMoreTokens()) {
                addNetwork((im.networking.Network) Class.forName(
                    networks.nextToken()).newInstance());
            }
            // MessageFactory
            java.util.StringTokenizer msgFactories = new java.util.StringTokenizer(
                settings.getProperty("im.model.MessageFactory"), ",");
            while (msgFactories.hasMoreTokens()) {
                addMessageFactory((im.model.MessageFactory) Class.forName(
                    msgFactories.nextToken()).newInstance());
            }
            // Create and register view
            getViewFactory().createContactListView(contactList);
            // Identity
            java.util.StringTokenizer identities = new java.util.StringTokenizer(
                getParameter("Identities"), ";");
            while (identities.hasMoreTokens()) {
                java.util.StringTokenizer identity = new java.util.StringTokenizer(
                    identities.nextToken(), ",");
                im.model.Identity id = new im.model.Identity();
                id.setUserId(identity.nextToken());
                id.setName(identity.nextToken());
                String networkName = identity.nextToken();
                for (int i = 0; i < getNetworks().size(); i++) {
                    if (getNetworkAt(i).getName().equals(networkName)) {
                        id.setNetwork(getNetworkAt(i));
                    }
                }
                if (id.getNetwork() == null) {
                    throw new ClassNotFoundException("Could not find network " + networkName + " for " + id.getUserId());
                }
                id.setPassword(identity.nextToken());
                contactList.addContact(id);
            }
            // Contact
            java.util.StringTokenizer contacts = new java.util.StringTokenizer(
                getParameter("Contacts"), ";");
            while (contacts.hasMoreTokens()) {
                java.util.StringTokenizer contact = new java.util.StringTokenizer(
                    contacts.nextToken(), ",");
                im.model.Contact c = new im.model.Contact();
                c.setUserId(contact.nextToken());
                c.setName(contact.nextToken());
                String networkName = contact.nextToken();
                for (int i = 0; i < getNetworks().size(); i++) {
                    if (getNetworkAt(i).getName().equals(networkName)) {
                        c.setNetwork(getNetworkAt(i));
                    }
                }
                if (c.getNetwork() == null) {
                    throw new ClassNotFoundException("Could not find network " + networkName + " for " + c.getUserId());
                }
                contactList.addContact(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
 }
