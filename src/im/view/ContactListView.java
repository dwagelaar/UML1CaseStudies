
package im.view;

/**
 * <p></p>
 */
public abstract class ContactListView implements java.util.Observer {

/**
 * <p>Represents ...</p>
 */
    private im.model.ContactList model = null;

/**
 * <p>Represents ...</p>
 */
    private java.util.List contactView = new java.util.ArrayList();

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
 * @param model 
 */
    public void setModel(im.model.ContactList model) {        
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
    public im.model.ContactList getModel() {        
        return model;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public java.util.List getContactViews() {        
        return contactView;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param index 
 * @return 
 */
    public im.view.ContactView getContactViewAt(int index) {        
        try {
            return (im.view.ContactView) contactView.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contactView 
 */
    public void addContactView(im.view.ContactView contactView) {        
        if (! this.contactView.contains(contactView)) {
            this.contactView.add(contactView);
            contactView.setListView(this);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contactView 
 * @param index 
 */
    public void insertContactView(im.view.ContactView contactView, int index) {        
        if (! this.contactView.contains(contactView)) {
            try {
            this.contactView.add(index, contactView);
        } catch (IndexOutOfBoundsException e) {
            this.contactView.add(contactView);
        };
            contactView.setListView(this);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contactView 
 */
    public void removeContactView(im.view.ContactView contactView) {        
        if (this.contactView.contains(contactView)) {
            this.contactView.remove(contactView);
            contactView.setListView(null);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public abstract void onContactChange(im.model.Contact c);
 }
