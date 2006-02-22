package im.view;


public interface ContactListView {
public abstract void addContact(java.lang.String c, int index);

public abstract void removeContact(int index);

public abstract void addListener(im.view.ContactListViewListener l);

public abstract int getSelectedContact();

public abstract void setEnabled(boolean enabled);

}

