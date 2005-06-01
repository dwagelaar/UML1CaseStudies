
package im.edit;

/**
 * <p></p>
 */
public class ContactEdit implements java.util.Observer {

/**
 * <p>Represents ...</p>
 */
    private im.model.Contact model = null;

/**
 * <p>Represents ...</p>
 */
    private im.edit.ContactListEdit listEdit = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param o 
 * @param arg 
 */
    public void update(java.util.Observable o, Object arg) {        
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

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param listEdit 
 */
    public void setListEdit(im.edit.ContactListEdit listEdit) {        
        if (this.listEdit != listEdit) {
            if (this.listEdit != null) this.listEdit.removeContactEdit(this);
            this.listEdit = listEdit;
            if (listEdit != null) listEdit.addContactEdit(this);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param model 
 */
    public void setModel(im.model.Contact model) {        
        // Begin subscribe stanza
        if (this.model != null) this.model.deleteObserver(this);
        // Begin original body
        this.model = model;
        // End original body
        if (model != null) model.addObserver(this);
        // End subscribe stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.edit.ContactListEdit getListEdit() {        
        return listEdit;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.Contact getModel() {        
        return model;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public  ContactEdit(im.model.Contact c) {        
        setModel(c);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param s 
 */
    public void onStatusChange(String s) {        
        onChange();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param n 
 */
    public void onNameChange(String n) {        
        onChange();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    private void onChange() {        
        	int index = getListEdit().getContactEdits().indexOf(this);
            if (index > -1) {
                getListEdit().getView().removeContact(index);
                getListEdit().getView().addContact(formatContact(), index);
            }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String formatContact() {        
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
 }
