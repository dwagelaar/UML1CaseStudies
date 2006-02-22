package im.edit;


public class ContactEdit implements java.util.Observer {
private im.edit.ContactListEdit listEdit = null;

private im.model.Contact model = null;

public void onStatusChange(java.lang.String s) {
        onChange();

}

public void onNameChange(java.lang.String n) {
        onChange();

}

public ContactEdit(im.model.Contact c) {
        setModel(c);

}

private void onChange() {
        	int index = getListEdit().getContactEdits().indexOf(this);
            if (index > -1) {
                getListEdit().getView().removeContact(index);
                getListEdit().getView().addContact(formatContact(), index);
            }

}

public java.lang.String formatContact() {
        String name = getModel().getName();
        String status = getModel().getStatus();
        if (getModel() instanceof im.model.Identity) {
            name = "* " + name;
        } else {
            name = "  " + name;
        }
        if (getModel().getNetwork() != null) {
            name += " - " + getModel().getNetwork().getName();
        }
        if (status == null) {
            status = "offline";
        }
        name += " (" + status + ")";
        return name;

}

public void setListEdit(im.edit.ContactListEdit listEdit) {
if (this.listEdit != listEdit) {
    if (this.listEdit != null) this.listEdit.removeContactEdit(this);
    this.listEdit = listEdit;
    if (listEdit != null) listEdit.addContactEdit(this);
}
}

public im.edit.ContactListEdit getListEdit() {
return listEdit;
}

public void setModel(im.model.Contact model) {
// Begin subscribe stanza
if (this.model != null) this.model.deleteObserver(this);
// Begin original body
this.model = model;
// End original body
if (model != null) model.addObserver(this);
// End subscribe stanza
}

public im.model.Contact getModel() {
return model;
}

public void update(java.util.Observable o, java.lang.Object arg) {
if (arg instanceof java.util.Hashtable) {
    java.util.Hashtable e = (java.util.Hashtable) arg;
    String mName = "on" + ((String) e.get("name")) + "Change";
    Class[] parmTypes = { (Class) e.get("class") };
    try {
        java.lang.reflect.Method m = getClass().getDeclaredMethod(mName, parmTypes);
        Object[] args = { e.get("value") };
        m.invoke(this, args);
    } catch (NoSuchMethodException nex) {
        // no handler
    } catch (Exception ex) {
        // wrong handler configuration
        ex.printStackTrace();
    }
}
}

}

