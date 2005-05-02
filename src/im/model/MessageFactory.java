
package im.model;
/**
 * <p></p>
 */
public interface MessageFactory {
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract im.model.Message createMessage();
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 * @return 
 */
    public abstract boolean isValidContent(Object c);
}









