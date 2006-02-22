package im.model;


public class Identity extends im.model.Contact {
private java.lang.String password;

public void setPassword(java.lang.String password) {
// Begin Observable stanza
if (this.password != password) {
    // Begin original body
this.password = password;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "Password");
    e.put("class", String.class);
    if (password != null) {
        e.put("value", password);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public java.lang.String getPassword() {
return password;
}

}

