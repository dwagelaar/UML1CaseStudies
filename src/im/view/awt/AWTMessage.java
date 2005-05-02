
package im.view.awt;
import java.awt.*;
import java.awt.event.*;

/**
 * <p></p>
 */
public class AWTMessage extends java.awt.Frame {

/**
 * <p>Represents ...</p>
 */
    private java.awt.TextArea messageField = new TextArea();

/**
 * <p>Represents ...</p>
 */
    private java.awt.Panel btnPanel = new Panel();

/**
 * <p>Represents ...</p>
 */
    private java.awt.Button sendBtn = new Button("Send");

/**
 * <p>Represents ...</p>
 */
    private java.awt.Button discardBtn = new Button("Discard");

/**
 * <p>Represents ...</p>
 */
    private boolean sendClicked = false;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param sendClicked 
 */
    public void setSendClicked(boolean sendClicked) {        
        this.sendClicked = sendClicked;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public boolean getSendClicked() {        
        return sendClicked;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  AWTMessage() {        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onDiscard();
            }
        });
        discardBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onDiscard();
            }
        });
        sendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSend();
            }
        });
        setTitle("Message");
        setLayout(new BorderLayout());
        add(messageField, BorderLayout.CENTER);
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(sendBtn);
        btnPanel.add(discardBtn);
        add(btnPanel, BorderLayout.SOUTH);
        pack();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param content 
 */
    public void setContent(Object content) {        
        messageField.setText(content.toString());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public Object getContent() {        
        return messageField.getText();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void onSend() {        
        setSendClicked(true);
        setVisible(false);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void onDiscard() {        
        setSendClicked(false);
        setVisible(false);
    } 
 }
