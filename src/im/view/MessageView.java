
package im.view;

/**
 * <p></p>
 */
public abstract class MessageView implements java.util.Observer {

/**
 * <p>Represents ...</p>
 */
    private im.model.Message model = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param o 
 * @param arg 
 */
    public void update(java.util.Observable o, Object arg) {        
        if (arg instanceof java.util.Hashtable) {
            java.util.Hashtable e = (java.util.Hashtable) arg;
            String mName = "on" + ((String) e.get("name")) + "Change";
            Class[] parmTypes = { (Class) e.get("class") };
            try {
                java.lang.reflect.Method m = getClass().getDeclaredMethod(mName, parmTypes);
                Object[] args = { e.get("value") };
                m.invoke(this, args);
            } catch (NoSuchMethodException nex) {
                // no handler
            } catch (Exception ex) {
                // wrong handler configuration
                ex.printStackTrace();
            }
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param model 
 */
    public void setModel(im.model.Message model) {        
        // Begin subscribe stanza
        if (this.model != null) this.model.deleteObserver(this);
        // Begin original body
        this.model = model;
        // End original body
        if (model != null) model.addObserver(this);
        // End subscribe stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.Message getModel() {        
        return model;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param sender 
 */
    public abstract void onSenderChange(String sender);

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param recipient 
 */
    public abstract void onRecipientChange(String recipient);

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param content 
 */
    public abstract void onContentChange(Object content);
 }
