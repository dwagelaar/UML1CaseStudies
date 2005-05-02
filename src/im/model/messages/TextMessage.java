
package im.model.messages;

/**
 * <p></p>
 */
public class TextMessage extends im.model.Message {

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  TextMessage() {        
        setContent(new String());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param content 
 */
    public void setContent(Object content) {        
        if (isValidContent(content)) {
            super.setContent(content);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 * @param content 
 */
    public boolean isValidContent(Object content) {        
        return (content instanceof String);
    } 
 }
