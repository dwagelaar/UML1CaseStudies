package im.model;


public class Message extends im.model.NetworkSpecificData {
private java.lang.String sender;

private java.lang.String recipient;

private java.lang.Object content;

private im.model.Conversation conversation = null;

public void send() {
        if (getNetwork() != null) {
            getNetwork().send(this);
        }

}

public void setConversation(im.model.Conversation conversation) {
// Begin Observable stanza
if (this.conversation != conversation) {
    // Begin original body
if (this.conversation != conversation) {
    this.conversation = conversation;
    conversation.setMessage(this);
}
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "Conversation");
    e.put("class", im.model.Conversation.class);
    if (conversation != null) {
        e.put("value", conversation);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public im.model.Conversation getConversation() {
return conversation;
}

public void setContent(java.lang.Object content) {
// Begin Observable stanza
if (this.content != content) {
    // Begin original body
this.content = content;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "Content");
    e.put("class", Object.class);
    if (content != null) {
        e.put("value", content);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public java.lang.Object getContent() {
return content;
}

public void setRecipient(java.lang.String recipient) {
// Begin Observable stanza
if (this.recipient != recipient) {
    // Begin original body
this.recipient = recipient;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "Recipient");
    e.put("class", String.class);
    if (recipient != null) {
        e.put("value", recipient);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public java.lang.String getRecipient() {
return recipient;
}

public void setSender(java.lang.String sender) {
// Begin Observable stanza
if (this.sender != sender) {
    // Begin original body
this.sender = sender;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "Sender");
    e.put("class", String.class);
    if (sender != null) {
        e.put("value", sender);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public java.lang.String getSender() {
return sender;
}

}

