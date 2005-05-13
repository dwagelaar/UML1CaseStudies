
package im.view.swing;
import im.view.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

/**
 * <p></p>
 */
public class SwingContactListView extends javax.swing.JPanel implements im.view.ContactListView {

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JList list = new JList();

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JButton addContactBtn = new JButton("Add");

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JButton removeContactBtn = new JButton("Remove");

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JPanel btnPanel = new JPanel();

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector listeners = new Vector();

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JScrollPane listScroll = new JScrollPane();

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  SwingContactListView() {        
        addContactBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notifyAddClicked();
            }
        });
        removeContactBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notifyRemoveClicked();
            }
        });
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    notifyContactAction();
                }
            }
        });
        setLayout(new BorderLayout());
        listScroll.getViewport().setView(list);
        add(listScroll, BorderLayout.CENTER);
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(addContactBtn);
        btnPanel.add(removeContactBtn);
        add(btnPanel, BorderLayout.SOUTH);
        im.InstantMessagingClient.getInstance().add(this);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 * @param index 
 */
    public void addContact(im.model.Contact contact, int index) {        
        String name = contact.getName();
        String status = contact.getStatus();
        if (contact instanceof im.model.Identity) {
            name = "* " + name;
        } else {
            name = "  " + name;
        }
        if (contact.getNetwork() != null) {
            name += " - " + contact.getNetwork().getName();
        }
        if (status == null) {
            status = "offline";
        }
        name += " (" + status + ")";
        listAdd(name, index);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param index 
 */
    public void removeContact(int index) {        
        listRemove(index);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public int getSelectedContact() {        
        return list.getSelectedIndex();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param l 
 */
    public void addListener(im.view.ContactListViewListener l) {        
        listeners.addElement(l);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void notifyAddClicked() {        
        for (int i = 0; i < listeners.size(); i++) {
            ((ContactListViewListener) listeners.elementAt(i)).onContactListAdd();
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void notifyRemoveClicked() {        
        for (int i = 0; i < listeners.size(); i++) {
            ((ContactListViewListener) listeners.elementAt(i)).onContactListRemove();
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void notifyContactAction() {        
        for (int i = 0; i < listeners.size(); i++) {
            ((ContactListViewListener) listeners.elementAt(i)).onContactListAction();
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param item 
 * @param index 
 */
    private void listAdd(String item, int index) {        
        Vector listData = new Vector();
        ListModel listModel = list.getModel();
        for (int i = 0; i < listModel.getSize(); i++) {
            if (i == index) {
                listData.addElement(item);
            }
            listData.addElement(listModel.getElementAt(i));
        }
        if (index == listModel.getSize()) {
            listData.addElement(item);
        }
        list.setListData(listData);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param index 
 */
    private void listRemove(int index) {        
        Vector listData = new Vector();
        ListModel listModel = list.getModel();
        for (int i = 0; i < listModel.getSize(); i++) {
            if (i != index) {
                listData.addElement(listModel.getElementAt(i));
            }
        }
        list.setListData(listData);
    } 
 }
