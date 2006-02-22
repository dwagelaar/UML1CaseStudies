package im;


public interface INetwork {
public void login(java.lang.String nwid, java.lang.String uid, java.lang.String pwd);

public void logout(java.lang.String nwid);

public void reportError(java.lang.String error);

public void addContact(java.lang.String nwid, java.lang.String uid, java.lang.String name);

public void removeContact(java.lang.String nwid, java.lang.String uid);

}

