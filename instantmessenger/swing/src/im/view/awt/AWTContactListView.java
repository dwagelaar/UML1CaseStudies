
package im.view.awt;
import im.view.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * <p></p>
 */
public class AWTContactListView extends java.awt.Panel implements im.view.ContactListView {

/**
 * <p>Represents ...</p>
 */
    private java.awt.List list = new List();

/**
 * <p>Represents ...</p>
 */
    private java.awt.Button addContactBtn = new Button("Add");

/**
 * <p>Represents ...</p>
 */
    private java.awt.Button removeContactBtn = new Button("Remove");

/**
 * <p>Represents ...</p>
 */
    private java.awt.Panel btnPanel = new Panel();

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector listeners = new Vector();

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  AWTContactListView() {        
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
        list.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notifyContactAction();
            }
        });
        setLayout(new BorderLayout());
        add(list, BorderLayout.CENTER);
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
    public void addContact(String contact, int index) {        
        list.add(contact, index);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param index 
 */
    public void removeContact(int index) {        
        list.remove(index);
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
 }
