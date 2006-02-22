package im.model;


public class Contact extends im.model.NetworkSpecificData {
private java.lang.String userId;

private java.lang.String name;

private java.lang.String status;

private im.model.ContactList list = null;

public void setUserId(java.lang.String userId) {
// Begin Observable stanza
if (this.userId != userId) {
    // Begin original body
this.userId = userId;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "UserId");
    e.put("class", String.class);
    if (userId != null) {
        e.put("value", userId);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public java.lang.String getUserId() {
return userId;
}

public void setStatus(java.lang.String status) {
// Begin Observable stanza
if (this.status != status) {
    // Begin original body
this.status = status;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "Status");
    e.put("class", String.class);
    if (status != null) {
        e.put("value", status);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public java.lang.String getStatus() {
return status;
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

public void setList(im.model.ContactList list) {
// Begin Observable stanza
if (this.list != list) {
    // Begin original body
if (this.list != list) {
    if (this.list != null) this.list.removeContact(this);
    this.list = list;
    if (list != null) list.addContact(this);
}
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "List");
    e.put("class", im.model.ContactList.class);
    if (list != null) {
        e.put("value", list);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public im.model.ContactList getList() {
return list;
}

}

