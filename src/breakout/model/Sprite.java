
package breakout.model;

/**
 * <p></p>
 */
public abstract class Sprite extends java.util.Observable {

/**
 * <p>Represents ...</p>
 */
    private breakout.model.Field field = null;

/**
 * <p>Represents ...</p>
 */
    private breakout.model.Point position = null;

/**
 * <p>Represents ...</p>
 */
    private breakout.model.Dimension size = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param field 
 */
    public void setField(breakout.model.Field field) {        
        // Begin Observable stanza
        if (this.field != field) {
            // Begin original body
        if (this.field != field) {
            if (this.field != null) this.field.removeSprite(this);
            this.field = field;
            if (field != null) field.addSprite(this);
        }    // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Field");
            e.put("value", field);
            notifyObservers(e);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public breakout.model.Point getPosition() {        
        return position;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param size 
 */
    public void setSize(breakout.model.Dimension size) {        
        // Begin Observable stanza
        if (this.size != size) {
            // Begin original body
        this.size = size;    // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Size");
            e.put("value", size);
            notifyObservers(e);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param position 
 */
    public void setPosition(breakout.model.Point position) {        
        // Begin Observable stanza
        if (this.position != position) {
            // Begin original body
        this.position = position;    // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Position");
            e.put("value", position);
            notifyObservers(e);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public breakout.model.Dimension getSize() {        
        return size;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public breakout.model.Field getField() {        
        return field;
    } 
 }
