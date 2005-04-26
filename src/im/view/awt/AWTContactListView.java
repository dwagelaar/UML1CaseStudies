
package im.view.awt;

/**
 * <p></p>
 */
public class AWTContactListView extends im.view.ContactListView {

/**
 * <p>Represents ...</p>
 */
    private im.view.awt.AWTNewContactDialog newContactDlg = null;

/**
 * <p>Represents ...</p>
 */
    private im.view.awt.AWTContactList impl = new AWTContactList();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param newContactDlg 
 */
    public void setNewContactDlg(im.view.awt.AWTNewContactDialog newContactDlg) {        
        this.newContactDlg = newContactDlg;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param impl 
 */
    public void setImpl(im.view.awt.AWTContactList impl) {        
        this.impl = impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.awt.AWTNewContactDialog getNewContactDlg() {        
        return newContactDlg;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.awt.AWTContactList getImpl() {        
        return impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  AWTContactListView() {        
        getImpl().addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                onWindowClosing();
            }
        });
        getImpl().addAddActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onAddBtnClicked();
            }
        });
        getImpl().addRemoveActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onRemoveBtnClicked();
            }
        });
        getImpl().addContactActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onContactDoubleClicked();
            }
        });
        getImpl().setVisible(true);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 */
    public void onContactChange(im.model.Contact contact) {        
        int index = getModel().getContacts().indexOf(contact);
        if (index > -1) {
            AWTContactView view = (AWTContactView)
                im.InstantMessagingClient.getInstance().getViewFactory().createContactView(contact);
            view.setImpl(getImpl());
            getImpl().addContact(contact.getName(), index);
            insertContactView(view, index);
        } else {
            index = getContactViewIndex(contact);
            getImpl().removeContact(index);
            removeContactView(getContactViewAt(index));
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onAddBtnClicked() {        
        AWTNewContactDialog dlg = new AWTNewContactDialog(getImpl());
        setNewContactDlg(dlg);
        dlg.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent e) {
                onNewContactDlgClose();
            }
        });
        dlg.setModal(true);
        dlg.setVisible(true);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onRemoveBtnClicked() {        
        int index = getImpl().getSelectedIndex();
        if (index > -1) {
            getModel().removeContact(getModel().getContactAt(index));
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onContactDoubleClicked() {        
        int index = getImpl().getSelectedIndex();
        if (index > -1) {
            im.model.Contact c = getContactViewAt(index).getModel();
            // 1. new message dialog, in which message type gets chosen
            // 2. new message gets created, along with view
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onNewContactDlgClose() {        
        AWTNewContactDialog dlg = getNewContactDlg();
        if (dlg.getOkClicked()) {
            im.model.Contact c = new im.model.Contact();
            c.setNetwork(dlg.getNetwork());
            c.setUserId(dlg.getUid());
            c.setName(dlg.getName());
            getModel().addContact(c);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 * @return 
 */
    public int getContactViewIndex(im.model.Contact contact) {        
        for (int i = 0; i < getContactViews().size(); i++) {
            if (getContactViewAt(i).getModel() == contact)
                return i;
        }
        return -1;
    } 
 }
