
package breakout.view.awt;

/**
 * <p></p>
 */
public class AWTFieldView extends breakout.view.FieldView {

/**
 * <p>Represents ...</p>
 */
    private breakout.view.awt.AWTField impl = new AWTField();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param view 
 */
    private void addView(breakout.view.SpriteView view) {        
        java.awt.Component c = getComponent(view);
        if (c == null) {
            return;
        }
        getImpl().add(c);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param view 
 * @return 
 */
    private java.awt.Component getComponent(breakout.view.SpriteView view) {        
        java.awt.Component c = null;
        if (view instanceof AWTBallView) {
            c = ((AWTBallView) view).getImpl();
        } else if (view instanceof AWTBrickView) {
            c = ((AWTBrickView) view).getImpl();
        } else if (view instanceof AWTPaddleView) {
            c = ((AWTPaddleView) view).getImpl();
        }
        return c;
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
 * @param field 
 */
    public void onFieldChange(breakout.model.Field field) {        
        // stub
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param pos 
 */
    public void onPositionChange(breakout.model.Point pos) {        
        getImpl().setLocation(pos.getX(), pos.getY());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param sprite 
 */
    public void onSpriteChange(breakout.model.Sprite sprite) {        
        breakout.model.Field field = (breakout.model.Field) getModel();
        if (field.getSprites().contains(sprite)) {
            breakout.view.SpriteView view = getFactory().createSpriteView(sprite);
            addView(view);
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public breakout.view.awt.AWTField getImpl() {        
        return impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param impl 
 */
    public void setImpl(breakout.view.awt.AWTField impl) {        
        this.impl = impl;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void init() {        
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        breakout.model.Dimension size = new breakout.model.Dimension();
        size.setWidth(screenSize.width);
        size.setHeight(screenSize.height);
        getModel().setSize(size);
        getImpl().setVisible(true);
    } 
 }
