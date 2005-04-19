
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
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param list 
 */
    public void setList(im.model.ContactList list) {        
        if (this.list != list) {
            if (this.list != null) this.list.removeContact(this);
            this.list = list;
            if (list != null) list.addContact(this);
        }
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

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param name 
 */
    public void setName(String name) {        
        this.name = name;
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
    public String getName() {        
        return name;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param userId 
 */
    public void setUserId(String userId) {        
        this.userId = userId;
    } 
 }
