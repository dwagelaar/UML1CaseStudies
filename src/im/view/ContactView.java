
package im.view;

/**
 * <p></p>
 */
public abstract class ContactView implements java.util.Observer {

/**
 * <p>Represents ...</p>
 */
    private im.model.Contact model = null;

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
            Class[] parmTypes = { e.get("value").getClass() };
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
    public void setModel(im.model.Contact model) {        
        this.model = model;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.Contact getModel() {        
        return model;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public abstract void onListChange();

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public abstract void onUserIdChange();

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public abstract void onNameChange();
 }
