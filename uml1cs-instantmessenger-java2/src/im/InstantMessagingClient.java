package im;


public class InstantMessagingClient extends java.applet.Applet implements java.util.Observer, im.ExceptionReporter {
private im.view.ViewFactory viewFactory = null;

private java.util.List network = new java.util.ArrayList();

private java.util.List conversation = new java.util.ArrayList();

private im.model.ContactList contactList = new im.model.ContactList();

private static im.InstantMessagingClient instance = null;

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

public void init() {
instance = this;
setLayout(new java.awt.BorderLayout());
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

public void start() {
        for (int i = 0; i < getNetworks().size(); i++) {
            im.model.Identity id = getContactList().getIdentity(getNetworkAt(i));
            if (id != null) {
                getNetworkAt(i).login(id.getUserId(), id.getPassword());
            }
        }

}

public void stop() {
        for (int i = 0; i < getNetworks().size(); i++) {
            if (getContactList().getIdentity(getNetworkAt(i)) != null) {
                getNetworkAt(i).logout();
            }
        }

}

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

public void setContactList(im.model.ContactList contactList) {
this.contactList = contactList;
}

public im.model.ContactList getContactList() {
return contactList;
}

public void setViewFactory(im.view.ViewFactory viewFactory) {
this.viewFactory = viewFactory;
}

public im.view.ViewFactory getViewFactory() {
return viewFactory;
}

public java.util.List getConversations() {
return conversation;
}

public void addConversation(im.model.Conversation conversation) {
this.conversation.add(conversation);
}

public void removeConversation(im.model.Conversation conversation) {
this.conversation.remove(conversation);
}

public im.model.Conversation getConversationAt(int index) {
try {
    return (im.model.Conversation) conversation.get(index);
} catch (IndexOutOfBoundsException e) {
    return null;
}
}

public void insertConversation(im.model.Conversation conversation, int index) {
try {
    this.conversation.add(index, conversation);
} catch (IndexOutOfBoundsException e) {
    this.conversation.add(conversation);
};
}

public java.util.List getNetworks() {
return network;
}

public void addNetwork(im.networking.Network network) {
// Begin subscribe stanza
// Begin original body
this.network.add(network);
// End original body
if (network != null) network.addObserver(this);
// End subscribe stanza
}

public void removeNetwork(im.networking.Network network) {
// Begin subscribe stanza
if (network != null) network.deleteObserver(this);
// Begin original body
this.network.remove(network);
// End original body
// End subscribe stanza
}

public im.networking.Network getNetworkAt(int index) {
try {
    return (im.networking.Network) network.get(index);
} catch (IndexOutOfBoundsException e) {
    return null;
}
}

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

public void update(java.util.Observable o, java.lang.Object arg) {
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

public static im.InstantMessagingClient getInstance() {
if (instance == null) {
    instance = new InstantMessagingClient();
}
return instance;
}

public java.lang.String getAppletInfo() {
return "Title: InstantMessagingClient\n(C) 2005, Dennis Wagelaar, System and Software Engineering Lab, Vrije Universiteit Brussel\nA simple instant messaging client generated from a PIM";
}

}

