
package im.edit;

/**
 * <p></p>
 */
public class ContactListEdit implements java.util.Observer, im.view.ContactListViewListener, im.view.NewContactDialogListener {

/**
 * <p>Represents ...</p>
 */
    private im.view.ContactListView view = null;

/**
 * <p>Represents ...</p>
 */
    private java.util.List contactEdit = new java.util.ArrayList();

/**
 * <p>Represents ...</p>
 */
    private im.model.ContactList model = null;

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
 * @param model 
 */
    public void setModel(im.model.ContactList model) {        
        // Begin subscribe stanza
        if (this.model != null) this.model.deleteObserver(this);
        // Begin original body
        this.model = model;
        // End original body
        if (model != null) model.addObserver(this);
        // End subscribe stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param view 
 */
    public void setView(im.view.ContactListView view) {        
        this.view = view;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.ContactList getModel() {        
        return model;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.ContactListView getView() {        
        return view;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public java.util.List getContactEdits() {        
        return contactEdit;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param index 
 * @return 
 */
    public im.edit.ContactEdit getContactEditAt(int index) {        
        try {
            return (im.edit.ContactEdit) contactEdit.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contactEdit 
 */
    public void addContactEdit(im.edit.ContactEdit contactEdit) {        
        if (! this.contactEdit.contains(contactEdit)) {
            this.contactEdit.add(contactEdit);
            contactEdit.setListEdit(this);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contactEdit 
 * @param index 
 */
    public void insertContactEdit(im.edit.ContactEdit contactEdit, int index) {        
        if (! this.contactEdit.contains(contactEdit)) {
            try {
            this.contactEdit.add(index, contactEdit);
        } catch (IndexOutOfBoundsException e) {
            this.contactEdit.add(contactEdit);
        };
            contactEdit.setListEdit(this);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contactEdit 
 */
    public void removeContactEdit(im.edit.ContactEdit contactEdit) {        
        if (this.contactEdit.contains(contactEdit)) {
            this.contactEdit.remove(contactEdit);
            contactEdit.setListEdit(null);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public  ContactListEdit(im.model.ContactList c) {        
        setModel(c);
        im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
        setView(client.getViewFactory().createContactListView());
        getView().addListener(this);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public void onContactChange(im.model.Contact c) {        
        int index = getModel().getContacts().indexOf(c);
        if (index > -1) {
            ContactEdit edit = new ContactEdit(c);
            insertContactEdit(edit, index);
            getView().addContact(edit.formatContact(), index);
        } else {
            index = getContactEditIndex(c);
            getView().removeContact(index);
            removeContactEdit(getContactEditAt(index));
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onContactListAdd() {        
        getView().setEnabled(false);
        im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
        im.view.NewContactDialog dlg =
            client.getViewFactory().createNewContactDialog();
        for (int i = 0; i < client.getNetworks().size(); i++) {
            dlg.addNetwork(client.getNetworkAt(i).getName());
        }
        dlg.addListener(this);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onContactListRemove() {        
        int index = getView().getSelectedContact();
        if (index > -1) {
            im.model.Contact c = getModel().getContactAt(index);
            if (c instanceof im.model.Identity) {
                im.model.Identity id = (im.model.Identity) c;
                id.getNetwork().logout();
            } else {
                c.getNetwork().removeContact(c);
            }
            getModel().removeContact(c);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onContactListAction() {        
        int index = getView().getSelectedContact();
        if (index > -1) {
            im.model.Contact c = getModel().getContactAt(index);
            im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
            // reuse existing conversation, if any
            for (int i = 0; i < client.getConversations().size(); i++) {
                im.model.Contact contact = client.getConversationAt(i).getContact();
                if (contact.equals(c)) {
                    client.getConversationAt(i).setContact(new im.model.Contact());
                    client.getConversationAt(i).setContact(c);
                    return;
                }
            }
            // else new conversation
            im.model.Conversation conv = new im.model.Conversation();
            new ConversationEdit(conv);
            client.addConversation(conv);
            conv.setContact(c);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param okClicked 
 * @param dlg 
 */
    public void onNewContactDialogClose(boolean okClicked, im.view.NewContactDialog dlg) {        
        if (okClicked) {
            im.model.Contact c;
            im.networking.Network n =
                im.InstantMessagingClient.getInstance().getNetworkAt(dlg.getNetwork());
            if (getModel().getIdentity(n) == null) {
                im.model.Identity id = new im.model.Identity();
                id.setNetwork(n);
                id.setUserId(dlg.getUid());
                id.setName(dlg.getName());
                id.setPassword(dlg.getPassword());
                c = id;
                getModel().addContact(c);
                id.getNetwork().login(id.getUserId(), id.getPassword());
            } else {
                c = new im.model.Contact();
                c.setNetwork(n);
                c.setUserId(dlg.getUid());
                c.setName(dlg.getName());
                getModel().addContact(c);
                c.getNetwork().addContact(c);
            }
        }
        getView().setEnabled(true);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 * @return 
 */
    private int getContactEditIndex(im.model.Contact contact) {        
        for (int i = 0; i < getContactEdits().size(); i++) {
            if (getContactEditAt(i).getModel() == contact)
                return i;
        }
        return -1;
    } 
 }
