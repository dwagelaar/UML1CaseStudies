
package im.view.awt;
import im.view.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * <p></p>
 */
public class AWTNewContactDialog extends java.awt.Frame implements im.view.NewContactDialog {

/**
 * <p>Represents ...</p>
 */
    private java.awt.Panel dlgPanel = new Panel();

/**
 * <p>Represents ...</p>
 */
    private java.awt.Panel btnPanel = new Panel();

/**
 * <p>Represents ...</p>
 */
    private java.awt.Button okBtn = new Button("OK");

/**
 * <p>Represents ...</p>
 */
    private java.awt.Button cancelBtn = new Button("Cancel");

/**
 * <p>Represents ...</p>
 */
    private java.awt.Label uid = new Label("User ID");

/**
 * <p>Represents ...</p>
 */
    private java.awt.Label name = new Label("Name");

/**
 * <p>Represents ...</p>
 */
    private java.awt.TextField uidField = new TextField();

/**
 * <p>Represents ...</p>
 */
    private java.awt.TextField nameField = new TextField();

/**
 * <p>Represents ...</p>
 */
    private java.awt.Label network = new Label("Network");

/**
 * <p>Represents ...</p>
 */
    private java.awt.Choice networkField = new Choice();

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector listeners = new Vector();

/**
 * <p>Represents ...</p>
 */
    private java.awt.Label password = new Label("Password");

/**
 * <p>Represents ...</p>
 */
    private java.awt.TextField passwordField = new TextField();

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  AWTNewContactDialog() {        
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
        im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
        for (int i = 0; i < client.getNetworks().size(); i++) {
            networkField.add(client.getNetworkAt(i).getName());
        }
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

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getUid() {        
        return uidField.getText();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getName() {        
        return nameField.getText();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.networking.Network getNetwork() {        
        int index = networkField.getSelectedIndex();
        return im.InstantMessagingClient.getInstance().getNetworkAt(index);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getPassword() {        
        return passwordField.getText();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param l 
 */
    public void addListener(im.view.NewContactDialogListener l) {        
        listeners.addElement(l);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void onOk() {        
        setVisible(false);
        notifyListeners(true);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void onCancel() {        
        setVisible(false);
        notifyListeners(false);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param okClicked 
 */
    private void notifyListeners(boolean okClicked) {        
        for (int i = 0; i < listeners.size(); i++) {
            ((NewContactDialogListener) listeners.elementAt(i)).onNewContactDialogClose(okClicked, this);
        }
    } 
 }
