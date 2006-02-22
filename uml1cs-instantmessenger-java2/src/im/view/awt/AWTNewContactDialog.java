package im.view.awt;

import java.awt.event.*;
import im.view.*;
import java.awt.*;
import java.util.Vector;

public class AWTNewContactDialog extends java.awt.Frame implements im.view.NewContactDialog {
private java.awt.Label password = new Label("Password");

private java.awt.Label name = new Label("Name");

private java.awt.Panel dlgPanel = new Panel();

private java.awt.TextField passwordField = new TextField();

private java.awt.TextField uidField = new TextField();

private java.awt.Label uid = new Label("User ID");

private java.awt.Choice networkField = new Choice();

private java.awt.Label network = new Label("Network");

private java.util.Vector listeners = new Vector();

private java.awt.Panel btnPanel = new Panel();

private java.awt.Button okBtn = new Button("OK");

private java.awt.TextField nameField = new TextField();

private java.awt.Button cancelBtn = new Button("Cancel");

private void onCancel() {
        setVisible(false);
        notifyListeners(false);

}

private void notifyListeners(boolean okClicked) {
        for (int i = 0; i < listeners.size(); i++) {
            ((NewContactDialogListener) listeners.elementAt(i)).onNewContactDialogClose(okClicked, this);
        }

}

public java.lang.String getPassword() {
        return passwordField.getText();

}

private void onOk() {
        setVisible(false);
        notifyListeners(true);

}

public java.lang.String getUid() {
        return uidField.getText();

}

public void addNetwork(java.lang.String n) {
        networkField.add(n);

}

public int getNetwork() {
        return networkField.getSelectedIndex();

}

public AWTNewContactDialog() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOk();
            }
        });
        setTitle("Add new contact");
        setLayout(new BorderLayout());
        dlgPanel.setLayout(new GridLayout(4,2));
        dlgPanel.add(network);
        dlgPanel.add(networkField);
        dlgPanel.add(uid);
        dlgPanel.add(uidField);
        dlgPanel.add(name);
        dlgPanel.add(nameField);
        dlgPanel.add(password);
        passwordField.setEchoChar('*');
        dlgPanel.add(passwordField);
        add(dlgPanel, BorderLayout.CENTER);
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);
        add(btnPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);

}

public java.lang.String getName() {
        return nameField.getText();

}

public void addListener(im.view.NewContactDialogListener l) {
        listeners.addElement(l);

}

}

