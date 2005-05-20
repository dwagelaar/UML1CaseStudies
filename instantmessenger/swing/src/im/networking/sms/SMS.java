
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
 }
