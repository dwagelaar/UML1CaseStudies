
package im.model;

/**
 * <p></p>
 */
public class Contact extends im.model.NetworkSpecificData {

/**
 * <p>Represents ...</p>
 */
    private im.model.ContactList list = null;

/**
 * <p>Represents ...</p>
 */
    private String userId;

/**
 * <p>Represents ...</p>
 */
    private String name;

/**
 * <p>Represents ...</p>
 */
    private String status;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param name 
 */
    public void setName(String name) {        
        // Begin Observable stanza
        if (this.name != name) {
            // Begin original body
        this.name = name;
            // End original body
            notifyObservers("Name", name);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param list 
 */
    public void setList(im.model.ContactList list) {        
        // Begin Observable stanza
        if (this.list != list) {
            // Begin original body
        if (this.list != list) {
            if (this.list != null) this.list.removeContact(this);
            this.list = list;
            if (list != null) list.addContact(this);
        }
            // End original body
            notifyObservers("List", list);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param status 
 */
    public void setStatus(String status) {        
        // Begin Observable stanza
        if (this.status != status) {
            // Begin original body
        this.status = status;
            // End original body
            notifyObservers("Status", status);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param userId 
 */
    public void setUserId(String userId) {        
        // Begin Observable stanza
        if (this.userId != userId) {
            // Begin original body
        this.userId = userId;
            // End original body
            notifyObservers("UserId", userId);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getName() {        
        return name;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.ContactList getList() {        
        return list;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getStatus() {        
        return status;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String getUserId() {        
        return userId;
    } 
 }
