package im.networking;


public abstract class Network extends java.util.Observable {
private java.lang.String name;

private im.model.Message recvMsg;

private im.model.Contact recvContact;

public abstract void send(im.model.Message msg);

public void login(java.lang.String uid, java.lang.String pwd) {
        // your code here

}

public void logout() {

}

public void addContact(im.model.Contact c) {

}

public void removeContact(im.model.Contact c) {

}

public static Network[] getDefault() {
        String[] options = {
            "im.networking.jabber.mejabber.MEJabber",
            "im.networking.jabber.defaultjabber.DefaultJabber",
            "im.networking.sms.SMS",
            "im.networking.local.Local"
        };
        java.util.Vector networks = new java.util.Vector();
        for (int i = 0; i < options.length; i++) {
            try {
                networks.addElement(Class.forName(options[i]).newInstance());
            } catch (Exception e) {
            }
        }
        Network[] nwArray = new Network[networks.size()];
        networks.copyInto(nwArray);
        return nwArray;

}

public void setName(java.lang.String name) {
// Begin Observable stanza
if (this.name != name) {
    // Begin original body
this.name = name;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "Name");
    e.put("class", String.class);
    if (name != null) {
        e.put("value", name);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public java.lang.String getName() {
return name;
}

public void setRecvMsg(im.model.Message recvMsg) {
// Begin Observable stanza
if (this.recvMsg != recvMsg) {
    // Begin original body
this.recvMsg = recvMsg;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "RecvMsg");
    e.put("class", im.model.Message.class);
    if (recvMsg != null) {
        e.put("value", recvMsg);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public im.model.Message getRecvMsg() {
return recvMsg;
}

public void setRecvContact(im.model.Contact recvContact) {
// Begin Observable stanza
if (this.recvContact != recvContact) {
    // Begin original body
this.recvContact = recvContact;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "RecvContact");
    e.put("class", im.model.Contact.class);
    if (recvContact != null) {
        e.put("value", recvContact);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public im.model.Contact getRecvContact() {
return recvContact;
}

}

