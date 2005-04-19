
package im.model.messages;

/**
 * <p></p>
 */
public class JabberMessageFactory implements im.model.MessageFactory {

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param forNw 
 * @return 
 */
    public im.model.Message createMessage(im.networking.Network forNw) {        
        JabberMessage msg = new JabberMessage();
        msg.setNetwork(forNw);
        return msg;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public String messageType() {        
        // your code here
        return null;
    } 
 }
