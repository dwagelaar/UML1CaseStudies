package im.model;


public abstract class NetworkSpecificData extends java.util.Observable {
private im.networking.Network network = null;

public void setNetwork(im.networking.Network network) {
// Begin Observable stanza
if (this.network != network) {
    // Begin original body
this.network = network;
    // End original body
    setChanged();
    java.util.Hashtable e = new java.util.Hashtable();
    e.put("name", "Network");
    e.put("class", im.networking.Network.class);
    if (network != null) {
        e.put("value", network);
    }
    notifyObservers(e);
}
// End Observable stanza
}

public im.networking.Network getNetwork() {
return network;
}

}

