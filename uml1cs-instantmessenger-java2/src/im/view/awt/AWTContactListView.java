package im.view.awt;

import java.awt.event.*;
import im.view.*;
import java.awt.*;
import java.util.Vector;

public class AWTContactListView extends java.awt.Panel implements im.view.ContactListView {
private java.awt.List list = new List();

private java.awt.Button removeContactBtn = new Button("Remove");

private java.awt.Panel btnPanel = new Panel();

private java.util.Vector listeners = new Vector();

private java.awt.Button addContactBtn = new Button("Add");

public AWTContactListView() {
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
        im.InstantMessagingClient.getInstance().setLayout(new BorderLayout());
        im.InstantMessagingClient.getInstance().add(this, BorderLayout.CENTER);

}

public void removeContact(int index) {
        list.remove(index);

}

public void addContact(java.lang.String contact, int index) {
        list.add(contact, index);

}

private void notifyRemoveClicked() {
        for (int i = 0; i < listeners.size(); i++) {
            ((ContactListViewListener) listeners.elementAt(i)).onContactListRemove();
        }

}

private void notifyContactAction() {
        for (int i = 0; i < listeners.size(); i++) {
            ((ContactListViewListener) listeners.elementAt(i)).onContactListAction();
        }

}

public int getSelectedContact() {
        return list.getSelectedIndex();

}

private void notifyAddClicked() {
        for (int i = 0; i < listeners.size(); i++) {
            ((ContactListViewListener) listeners.elementAt(i)).onContactListAdd();
        }

}

public void addListener(im.view.ContactListViewListener l) {
        listeners.addElement(l);

}

}

