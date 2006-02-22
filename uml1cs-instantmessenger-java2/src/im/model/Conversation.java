package im.model;


public class Conversation extends java.util.Observable {
private im.model.Contact contact = null;

private im.model.Message message = null;

public void setContact(im.model.Contact contact) {
// Begin Observable stanza
if (this.contact != contact) {
    // Begin original body
this.contact = contact;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "Contact");
    e.put("class", im.model.Contact.class);
    if (contact != null) {
        e.put("value", contact);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public im.model.Contact getContact() {
return contact;
}

public void setMessage(im.model.Message message) {
// Begin Observable stanza
if (this.message != message) {
    // Begin original body
if (this.message != message) {
    this.message = message;
    message.setConversation(this);
}
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "Message");
    e.put("class", im.model.Message.class);
    if (message != null) {
        e.put("value", message);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public im.model.Message getMessage() {
return message;
}

}

