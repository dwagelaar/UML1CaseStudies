
package breakout.view;

/**
 * <p></p>
 */
public abstract class FieldView extends breakout.view.SpriteView {

/**
 * <p>Represents ...</p>
 */
    private breakout.view.ViewFactory factory = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param factory 
 */
    public void setFactory(breakout.view.ViewFactory factory) {        
        this.factory = factory;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public breakout.view.ViewFactory getFactory() {        
        return factory;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param sprite 
 */
    public abstract void onSpriteChange(breakout.model.Sprite sprite);
 }
