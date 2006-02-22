package im;

import im.INetwork;

public class NetworkPort implements im.IClient {
private java.lang.String networkID = null;

public void recvNetworkID(java.lang.String nwid) {
        setNetworkID(nwid);

}

public void recvContact(java.lang.String nwid, java.lang.String uid, java.lang.String name, java.lang.String status) {
        im.model.Contact c = new im.model.Contact();
        c.setUserId(uid);
        c.setName(name);
        c.setStatus(status);
        im.InstantMessagingClient.getInstance().onRecvContactChange(c);

}

public void recvMessage(java.lang.String nwid, java.lang.String sender, java.lang.String recipient, java.lang.Object content) {
        im.model.Message m = new im.model.Message();
        m.setSender(sender);
        m.setRecipient(recipient);
        m.setContent(content);
        im.InstantMessagingClient.getInstance().onRecvMsgChange(m);

}

public void setNetworkID(java.lang.String networkID) {
this.networkID = networkID;
}

public java.lang.String getNetworkID() {
return networkID;
}

}

