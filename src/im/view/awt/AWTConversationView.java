
package im.view.awt;
import im.view.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * <p></p>
 */
public class AWTConversationView extends java.awt.Frame implements im.view.ConversationView {

/**
 * <p>Represents ...</p>
 */
    private java.awt.TextField messageField = new TextField();

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
    private java.awt.Panel convPanel = new Panel();

/**
 * <p>Represents ...</p>
 */
    private java.awt.TextArea textConvField = new TextArea();

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector listeners = new Vector();

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  AWTConversationView() {        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                notifyClose();
            }
        });
        sendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notifySendClicked();
            }
        });
        messageField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notifySendClicked();
            }
        });
        setTitle("Message");
        setLayout(new BorderLayout());
        textConvField.setEditable(false);
        convPanel.setLayout(new BorderLayout());
        add(convPanel, BorderLayout.CENTER);
        btnPanel.setLayout(new BorderLayout());
        btnPanel.add(sendBtn, BorderLayout.EAST);
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
    public Object getContent() {        
        return messageField.getText();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param content 
 */
    public void addContent(Object content) {        
        if (content instanceof String) {
            setTextEnabled(true);
            textConvField.append(content.toString() + "\n");
        } else if (content instanceof Image) {
            setTextEnabled(false);
            Image im = (Image) content;
            convPanel.setSize(im.getWidth(convPanel), im.getHeight(convPanel));
            Graphics g = convPanel.getGraphics();
            g.drawImage(im, 1, 1, convPanel);
            g.finalize();
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param enabled 
 */
    public void setTextEnabled(boolean enabled) {        
        if (enabled) {
            if (convPanel.getComponentCount() == 0) {
                convPanel.add(textConvField, BorderLayout.CENTER);
                btnPanel.add(messageField, BorderLayout.CENTER);
                pack();
            }
        } else {
            if (convPanel.getComponentCount() > 0) {
                convPanel.remove(textConvField);
                btnPanel.remove(messageField);
                pack();
            }
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param l 
 */
    public void addListener(im.view.ConversationViewListener l) {        
        listeners.addElement(l);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void notifyClose() {        
        for (int i = 0; i < listeners.size(); i++) {
            ((ConversationViewListener) listeners.elementAt(i)).onConversationClose();
        }
        setVisible(false);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void notifySendClicked() {        
        for (int i = 0; i < listeners.size(); i++) {
            ((ConversationViewListener) listeners.elementAt(i)).onConversationSend();
        }
        messageField.setText("");
    } 
 }
