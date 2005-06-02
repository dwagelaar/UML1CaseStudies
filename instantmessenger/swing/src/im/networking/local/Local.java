
package im.networking.local;

/**
 * <p></p>
 */
public class Local extends im.networking.Network implements im.ExceptionReporter {

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  Local() {        
        setName("Local");
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param msg 
 */
    public void send(im.model.Message msg) {        
        msg.setRecipient(msg.getSender());
        setRecvMsg(msg);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param e 
 */
    public void report(Exception e) {        
        im.model.Message msg = new im.model.Message();
        msg.setSender("System");
        msg.setContent(e.toString());
        send(msg);
        e.printStackTrace();
    } 
 }
