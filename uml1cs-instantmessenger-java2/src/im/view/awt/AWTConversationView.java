package im.view.awt;

import java.util.Vector;
import im.view.*;
import java.awt.event.*;
import java.awt.*;

public class AWTConversationView extends java.awt.Frame implements im.view.ConversationView {
private java.awt.TextField messageField = new TextField();

private java.awt.TextArea textConvField = new TextArea();

private java.awt.Panel convPanel = new Panel();

private java.util.Vector listeners = new Vector();

private java.awt.Button sendBtn = new Button("Send");

private java.awt.Panel btnPanel = new Panel();

public java.lang.Object getContent() {
        return messageField.getText();

}

public void addListener(im.view.ConversationViewListener l) {
        listeners.addElement(l);

}

public AWTConversationView() {
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
        convPanel.add(textConvField, BorderLayout.CENTER);
        add(convPanel, BorderLayout.CENTER);
        btnPanel.setLayout(new BorderLayout());
        btnPanel.add(sendBtn, BorderLayout.EAST);
        btnPanel.add(messageField, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);

}

private void notifyClose() {
        for (int i = 0; i < listeners.size(); i++) {
            ((ConversationViewListener) listeners.elementAt(i)).onConversationClose();
        }
        setVisible(false);

}

public void addContent(java.lang.Object content) {
        if (content instanceof String) {
            textConvField.append(content.toString() + "\n");
        } else if (content instanceof Image) {
            Image im = (Image) content;
            convPanel.setSize(im.getWidth(convPanel), im.getHeight(convPanel));
            Graphics g = convPanel.getGraphics();
            g.drawImage(im, 1, 1, convPanel);
            g.finalize();
        }

}

private void notifySendClicked() {
        for (int i = 0; i < listeners.size(); i++) {
            ((ConversationViewListener) listeners.elementAt(i)).onConversationSend();
        }
        messageField.setText("");

}

public void setVisible(boolean visible) {
        if (!isShowing() && visible) {
            pop();
        }
        super.setVisible(visible);

}

private void pop() {
        try {
            im.InstantMessagingClient.getInstance().play(getClass().getResource("pop.au"));
        } catch (Exception e) {
            //some VMs do not support audio
        }

}

}

