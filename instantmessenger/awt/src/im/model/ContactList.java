
package im.model;

/**
 * <p></p>
 */
public class ContactList extends java.util.Observable {

/**
 * <p>Represents ...</p>
 */
    private java.util.Vector contact = new java.util.Vector();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public java.util.Vector getContacts() {        
        return contact;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param index 
 * @return 
 */
    public im.model.Contact getContactAt(int index) {        
        try {
            return (im.model.Contact) contact.elementAt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 */
    public void addContact(im.model.Contact contact) {        
        // Begin Observable stanza
        if (! this.contact.contains(contact)) {
            // Begin original body
        if (! this.contact.contains(contact)) {
            this.contact.addElement(contact);
            contact.setList(this);
        }
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Contact");
            e.put("class", im.model.Contact.class);
            if (contact != null) {
                e.put("value", contact);
            }
            notifyObservers(e);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 * @param index 
 */
    public void insertContact(im.model.Contact contact, int index) {        
        // Begin Observable stanza
        if (! this.contact.contains(contact)) {
            // Begin original body
        if (! this.contact.contains(contact)) {
            try {
            this.contact.insertElementAt(contact, index);} catch (ArrayIndexOutOfBoundsException e) {
            this.contact.addElement(contact);
        };
            contact.setList(this);
        }
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Contact");
            e.put("class", im.model.Contact.class);
            if (contact != null) {
                e.put("value", contact);
            }
            notifyObservers(e);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 */
    public void removeContact(im.model.Contact contact) {        
        // Begin Observable stanza
        if (this.contact.contains(contact)) {
            // Begin original body
        if (this.contact.contains(contact)) {
            this.contact.removeElement(contact);
            contact.setList(null);
        }
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Contact");
            e.put("class", im.model.Contact.class);
            if (contact != null) {
                e.put("value", contact);
            }
            notifyObservers(e);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param forNw 
 * @return 
 */
    public im.model.Identity getIdentity(im.networking.Network forNw) {        
        for (int i = 0; i < getContacts().size(); i ++) {
            Contact contact = getContactAt(i);
            if ((contact instanceof Identity) && (contact.getNetwork() == forNw))
                return (Identity) contact;
        }
        return null;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param userId 
 * @return 
 */
    public String getUserName(String userId) {        
        for (int i = 0; i < getContacts().size(); i ++) {
            if (getContactAt(i).getUserId().equals(userId))
                return getContactAt(i).getName();
        }
        return userId;
    } 
 }
