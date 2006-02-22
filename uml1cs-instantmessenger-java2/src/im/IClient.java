package im;


public interface IClient {
public void recvNetworkID(java.lang.String nwid);

public void recvMessage(java.lang.String nwid, java.lang.String sender, java.lang.String recipient, java.lang.Object content);

public void recvContact(java.lang.String nwid, java.lang.String uid, java.lang.String name, java.lang.String status);

}

