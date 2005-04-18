
package breakout.view.awt;

/**
 * <p></p>
 */
public class AWTPaddleView extends breakout.view.PaddleView implements java.awt.event.MouseMotionListener {

/**
 * <p>Represents ...</p>
 */
    private breakout.view.awt.AWTPaddle impl = new AWTPaddle();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public breakout.view.awt.AWTPaddle getImpl() {        
        return impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param impl 
 */
    public void setImpl(breakout.view.awt.AWTPaddle impl) {        
        this.impl = impl;
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
 * @param e 
 */
    public void mouseDragged(java.awt.event.MouseEvent e) {        
        drag(e.getX());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  AWTPaddleView() {        
        impl.addMouseMotionListener(this);
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

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param e 
 */
    public void mouseMoved(java.awt.event.MouseEvent e) {        
        // stub
    } 
 }
