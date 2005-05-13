
package im.model.messages;

/**
 * <p></p>
 */
public class TextMessageFactory extends im.model.messages.MessageFactory {

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.messages.Message createMessage() {        
        return new TextMessage();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 * @return 
 */
    public boolean isValidContent(Object c) {        
        return (createMessage().isValidContent(c));
    } 
 }
