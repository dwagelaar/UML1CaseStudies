
package im.view.awt;
import java.awt.*;

/**
 * <p></p>
 */
public class AWTNewContactDialog extends java.awt.Dialog {

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
    private boolean okClicked = false;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param okClicked 
 */
    public void setOkClicked(boolean okClicked) {        
        this.okClicked = okClicked;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public boolean getOkClicked() {        
        return okClicked;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param owner 
 */
    public  AWTNewContactDialog(java.awt.Frame owner) {        
        super(owner);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                onCancel();
            }
        });
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onCancel();
            }
        });
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onOk();
            }
        });
        setTitle("Add new contact");
        setLayout(new BorderLayout());
        im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
        for (int i = 0; i < client.getNetworks().size(); i++) {
            networkField.add(client.getNetworkAt(i).getName());
        }
        dlgPanel.setLayout(new GridLayout(3,2));
        dlgPanel.add(network);
        dlgPanel.add(networkField);
        dlgPanel.add(uid);
        dlgPanel.add(uidField);
        dlgPanel.add(name);
        dlgPanel.add(nameField);
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
 */
    public void onOk() {        
        setOkClicked(true);
        setVisible(false);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onCancel() {        
        setOkClicked(false);
        setVisible(false);
    } 
 }
