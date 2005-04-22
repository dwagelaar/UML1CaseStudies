
package im.view.awt;
import java.awt.*;

/**
 * <p></p>
 */
public class AWTContactList extends java.awt.Frame {

/**
 * <p>Represents ...</p>
 */
    private java.awt.List list = new List();

/**
 * <p>Represents ...</p>
 */
    private java.awt.Button addContactBtn = new Button("Add contact");

/**
 * <p>Represents ...</p>
 */
    private java.awt.Button removeContactBtn = new Button("Remove contact");

/**
 * <p>Represents ...</p>
 */
    private java.awt.Panel btnPanel = new Panel();

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  AWTContactList() {        
        setTitle("Instant Messenger");
        setLayout(new BorderLayout());
        add(list, BorderLayout.CENTER);
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(addContactBtn);
        btnPanel.add(removeContactBtn);
        add(btnPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param name 
 * @param index 
 */
    public void addContact(String name, int index) {        
        list.add(name, index);
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
 }
