
package im.networking.sms;

/**
 * <p></p>
 */
public class SMS extends im.networking.Network {

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  SMS() {        
        setName("SMS");
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param msg 
 */
    public void send(im.model.Message msg) {        
        System.out.println("Sending " + msg + " via " + getName());
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param uid 
 * @param pwd 
 * @return 
 */
    public boolean login(String uid, String pwd) {        
        // no login required
        return true;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void logout() {        
        // no logout required
    } 
 }
