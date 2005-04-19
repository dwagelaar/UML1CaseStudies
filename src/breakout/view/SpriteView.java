
package breakout.view;

/**
 * <p></p>
 */
public abstract class SpriteView implements java.util.Observer {

/**
 * <p>Represents ...</p>
 */
    private breakout.model.Sprite model = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param pos 
 */
    public abstract void onPositionChange(breakout.model.Point pos);

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
 * @param size 
 */
    public abstract void onSizeChange(breakout.model.Dimension size);

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param field 
 */
    public abstract void onFieldChange(breakout.model.Field field);

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param model 
 */
    public void setModel(breakout.model.Sprite model) {        
        // Begin subscribe stanza
        if (this.model != null) this.model.deleteObserver(this);
        // Begin original body
        this.model = model;// End original body
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
    public breakout.model.Sprite getModel() {        
        return model;
    } 
 }
