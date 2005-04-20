
package im.model;

/**
 * <p></p>
 */
public class ContactList extends java.util.Observable {

/**
 * <p>Represents ...</p>
 */
    private java.util.List contact = new java.util.ArrayList();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public java.util.List getContacts() {        
        return contact;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 */
    public void addContact(im.model.Contact contact) {        
        if (! this.contact.contains(contact)) {
            this.contact.add(contact);
            contact.setList(this);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param contact 
 */
    public void removeContact(im.model.Contact contact) {        
        if (this.contact.contains(contact)) {
            this.contact.remove(contact);
            contact.setList(null);
        }
    } 
 }
