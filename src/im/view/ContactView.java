
package im.view;

/**
 * <p></p>
 */
public abstract class ContactView implements java.util.Observer {

/**
 * <p>Represents ...</p>
 */
    private im.model.Contact model = null;

/**
 * <p>Represents ...</p>
 */
    private im.view.ContactListView listView = null;

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
 * @param listView 
 */
    public void setListView(im.view.ContactListView listView) {        
        if (this.listView != listView) {
            if (this.listView != null) this.listView.removeContactView(this);
            this.listView = listView;
            if (listView != null) listView.addContactView(this);
        }
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
 * @return 
 */
    public im.view.ContactListView getListView() {        
        return listView;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param n 
 */
    public abstract void onNameChange(String n);

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param s 
 */
    public abstract void onStatusChange(String s);
 }
