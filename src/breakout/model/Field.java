
package breakout.model;
import java.util.*;

/**
 * <p></p>
 */
public class Field extends breakout.model.Sprite {

/**
 * <p>Represents ...</p>
 */
    private java.util.Collection sprite = new java.util.ArrayList();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public java.util.Collection getSprites() {        
        return sprite;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void newGame() {        
        // your code here
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param sprite 
 */
    public void removeSprite(breakout.model.Sprite sprite) {        
        // Begin Observable stanza
        if (this.sprite.contains(sprite)) {
            // Begin original body
        if (this.sprite.contains(sprite)) {
            this.sprite.remove(sprite);
            sprite.setField(null);
        }    // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Sprite");
            e.put("value", sprite);
            notifyObservers(e);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param sprite 
 */
    public void addSprite(breakout.model.Sprite sprite) {        
        // Begin Observable stanza
        if (! this.sprite.contains(sprite)) {
            // Begin original body
        if (! this.sprite.contains(sprite)) {
            this.sprite.add(sprite);
            sprite.setField(this);
        }    // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Sprite");
            e.put("value", sprite);
            notifyObservers(e);
        }
        // End Observable stanza
    } 
 }
