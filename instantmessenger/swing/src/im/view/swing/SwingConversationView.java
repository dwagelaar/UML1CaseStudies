
package im.view.swing;
import im.view.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

/**
 * <p></p>
 */
public class SwingConversationView extends javax.swing.JFrame implements im.view.ConversationView {

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JTextField messageField = new JTextField();

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JPanel btnPanel = new JPanel();

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JButton sendBtn = new JButton("Send");

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JPanel convPanel = new JPanel();

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JTextPane textConvField = new JTextPane();

/**
 * <p>Represents ...</p>
 */
    private javax.swing.JScrollPane textConvScroll = new JScrollPane();

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector listeners = new Vector();

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  SwingConversationView() {        
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
        textConvScroll.setPreferredSize(new Dimension(500, 300));
        textConvScroll.getViewport().setView(textConvField);
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
            textConvField.setText(textConvField.getText() + content.toString() + "\n");
        } else if (content instanceof Image) {
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
                convPanel.add(textConvScroll, BorderLayout.CENTER);
                btnPanel.add(messageField, BorderLayout.CENTER);
                pack();
            }
        } else {
            if (convPanel.getComponentCount() > 0) {
                convPanel.remove(textConvScroll);
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

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param visible 
 */
    public void setVisible(boolean visible) {        
        if (!isShowing() && visible) {
            pop();
        }
        super.setVisible(visible);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void toFront() {        
        if (!isActive()) {
            pop();
        }
        super.toFront();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void pop() {        
        im.InstantMessagingClient.getInstance().play(getClass().getResource("pop.wav"));
    } 
 }
