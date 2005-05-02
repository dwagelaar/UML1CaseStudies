
package im.view.awt;
import java.awt.*;

/**
 * <p></p>
 */
public class AWTConversation extends java.awt.Frame {

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
 * <p>Does ...</p>
 * 
 * 
 */
    public  AWTConversation() {        
        setTitle("Message");
        setLayout(new BorderLayout());
        textConvField.setEditable(false);
        convPanel.setLayout(new BorderLayout());
        add(convPanel, BorderLayout.CENTER);
        btnPanel.setLayout(new BorderLayout());
        btnPanel.add(sendBtn, BorderLayout.EAST);
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
        if (content == null) {
            messageField.setText("");
        } else if (content instanceof String) {
            messageField.setText(content.toString());
        }
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
            enableText();
            textConvField.append(content.toString() + "\n");
        } else if (content instanceof Image) {
            disableText();
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
 */
    public void enableText() {        
        if (convPanel.getComponentCount() == 0) {
            convPanel.add(textConvField, BorderLayout.CENTER);
            btnPanel.add(messageField, BorderLayout.CENTER);
            pack();
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void disableText() {        
            if (convPanel.getComponentCount() > 0) {
                convPanel.remove(textConvField);
                btnPanel.remove(messageField);
                pack();
        	}
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param listener 
 */
    public void addSendActionListener(java.awt.event.ActionListener listener) {        
        messageField.addActionListener(listener);
        sendBtn.addActionListener(listener);
    } 
 }
