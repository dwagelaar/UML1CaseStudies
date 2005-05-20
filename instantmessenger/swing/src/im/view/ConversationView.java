
package im.view;
/**
 * <p></p>
 */
public interface ConversationView {
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract Object getContent();
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public abstract void addContent(Object c);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param l 
 */
    public abstract void addListener(im.view.ConversationViewListener l);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param t 
 */
    public abstract void setTitle(String t);
/**
 * <p>Does ...</p>
 * 
 * 
 */
    public abstract void toFront();
}









