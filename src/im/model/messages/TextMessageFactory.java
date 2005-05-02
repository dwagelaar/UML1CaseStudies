
package im.model.messages;

/**
 * <p></p>
 */
public class TextMessageFactory implements im.model.MessageFactory {

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.Message createMessage() {        
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
