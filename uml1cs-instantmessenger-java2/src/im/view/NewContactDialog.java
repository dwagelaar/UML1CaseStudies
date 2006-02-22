package im.view;


public interface NewContactDialog {
public abstract java.lang.String getUid();

public abstract java.lang.String getName();

public abstract int getNetwork();

public abstract void addListener(im.view.NewContactDialogListener l);

public abstract java.lang.String getPassword();

public abstract void addNetwork(java.lang.String n);

}

