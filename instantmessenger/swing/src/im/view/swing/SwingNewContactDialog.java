
package im.view.swing;
import im.view.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

/**
 * <p></p>
 */
public class SwingNewContactDialog extends javax.swing.JFrame implements im.view.NewContactDialog {

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JPanel dlgPanel = new JPanel();

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JPanel btnPanel = new JPanel();

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JButton okBtn = new JButton("OK");

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JButton cancelBtn = new JButton("Cancel");

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JLabel uid = new JLabel("User ID");

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JLabel name = new JLabel("Name");

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JTextField uidField = new JTextField();

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JTextField nameField = new JTextField();

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JLabel network = new JLabel("Network");

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JComboBox networkField = new JComboBox();

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector listeners = new Vector();

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JLabel password = new JLabel("Password");

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JPasswordField passwordField = new JPasswordField();

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  SwingNewContactDialog() {        
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
        getContentPane().setLayout(new BorderLayout());
        im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
        for (int i = 0; i < client.getNetworks().size(); i++) {
            networkField.addItem(makeObject(client.getNetworkAt(i).getName()));
        }
        dlgPanel.setLayout(new GridLayout(4,2));
        dlgPanel.add(network);
        dlgPanel.add(networkField);
        dlgPanel.add(uid);
        dlgPanel.add(uidField);
        dlgPanel.add(name);
        dlgPanel.add(nameField);
        dlgPanel.add(password);
        dlgPanel.add(passwordField);
        getContentPane().add(dlgPanel, BorderLayout.CENTER);
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param s 
 * @return 
 */
    private Object makeObject(final String s) {        
        return new Object() {
            public String toString() {
                return s;
            }
        };
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
 }
