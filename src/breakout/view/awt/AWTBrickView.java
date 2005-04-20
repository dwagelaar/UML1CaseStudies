
package breakout.view.awt;

/**
 * <p></p>
 */
public class AWTBrickView extends breakout.view.BrickView {

/**
 * <p>Represents ...</p>
 */
    private breakout.view.awt.AWTBrick impl = new AWTBrick();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param impl 
 */
    public void setImpl(breakout.view.awt.AWTBrick impl) {        
        this.impl = impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public breakout.view.awt.AWTBrick getImpl() {        
        return impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param size 
 */
    public void onSizeChange(breakout.model.Dimension size) {        
        impl.setSize(size.getWidth(), size.getHeight());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param pos 
 */
    public void onPositionChange(breakout.model.Point pos) {        
        impl.setLocation(pos.getX(), pos.getY());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param field 
 */
    public void onFieldChange(breakout.model.Field field) {        
        getImpl().removeFromParent();
    } 
 }
