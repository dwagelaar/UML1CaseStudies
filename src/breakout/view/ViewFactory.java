
package breakout.view;
/**
 * <p></p>
 */
public interface ViewFactory {
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param field 
 * @return 
 */
    public abstract breakout.view.FieldView createFieldView(breakout.model.Field field);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param brick 
 * @return 
 */
    public abstract breakout.view.BrickView createBrickView(breakout.model.Brick brick);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param ball 
 * @return 
 */
    public abstract breakout.view.BallView createBallView(breakout.model.Ball ball);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param paddle 
 * @return 
 */
    public abstract breakout.view.PaddleView createPaddleView(breakout.model.Paddle paddle);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param sprite 
 * @return 
 */
    public abstract breakout.view.SpriteView createSpriteView(breakout.model.Sprite sprite);
}









