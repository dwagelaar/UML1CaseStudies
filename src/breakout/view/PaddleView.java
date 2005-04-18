
package breakout.view;

/**
 * <p></p>
 */
public abstract class PaddleView extends breakout.view.SpriteView {

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param x 
 */
    public void drag(int x) {        
        breakout.model.Sprite sprite = getModel();
        breakout.model.Field field = sprite.getField();
        breakout.model.Point pos = sprite.getPosition();
        breakout.model.Dimension size = sprite.getSize();
        int newx = pos.getX() + x - size.getWidth()/2;
        if (newx < 0) {
            newx = 0;
        }
        else if (newx > field.getSize().getWidth() - size.getWidth()) { 
            newx = field.getSize().getWidth() - size.getWidth();
        }
    } 
 }
