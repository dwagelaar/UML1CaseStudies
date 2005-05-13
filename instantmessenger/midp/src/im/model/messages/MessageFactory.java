
package im.model.messages;

/**
 * <p></p>
 */
public abstract class MessageFactory {

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract im.model.messages.Message createMessage();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 * @return 
 */
    public abstract boolean isValidContent(Object c);

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public static im.model.messages.MessageFactory[] getDefault() {        
        String[] options = {
            "im.model.messages.TextMessageFactory"
        };
        java.util.Vector factories = new java.util.Vector();
        for (int i = 0; i < options.length; i++) {
            try {
                factories.addElement(Class.forName(options[i]).newInstance());
            } catch (Exception e) {
            }
        }
        MessageFactory[] mfArray = new MessageFactory[factories.size()];
        factories.copyInto(mfArray);
        return mfArray;
    } 
 }
