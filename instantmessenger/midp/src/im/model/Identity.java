
package im.model;

/**
 * <p></p>
 */
public class Identity extends im.model.Contact {

/**
 * <p>Represents ...</p>
 */
    private String password;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param password 
 */
    public void setPassword(String password) {        
        // Begin Observable stanza
        if (this.password != password) {
            // Begin original body
        this.password = password;
            // End original body
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Password");
            if (password != null) {
                e.put("value", password);
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
 * @return 
 */
    public String getPassword() {        
        return password;
    } 
 }
