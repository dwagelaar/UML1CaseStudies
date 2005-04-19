
package breakout.view.awt;

/**
 * <p></p>
 */
public class AWTBallView extends breakout.view.BallView {

/**
 * <p>Represents ...</p>
 */
    private breakout.view.awt.AWTBall impl = new AWTBall();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param impl 
 */
    public void setImpl(breakout.view.awt.AWTBall impl) {        
        this.impl = impl;
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
 * @param size 
 */
    public void onSizeChange(breakout.model.Dimension size) {        
        getImpl().setSize(size.getWidth(), size.getHeight());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public breakout.view.awt.AWTBall getImpl() {        
        return impl;
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
