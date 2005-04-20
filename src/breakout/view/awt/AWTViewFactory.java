
package breakout.view.awt;

/**
 * <p></p>
 */
public class AWTViewFactory implements breakout.view.ViewFactory {

/**
 * <p>Represents ...</p>
 */
    private breakout.view.SpriteView view;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param view 
 * @param sprite 
 */
    private void initSpriteView(breakout.view.SpriteView view, breakout.model.Sprite sprite) {        
        this.view = view;
        view.setModel(sprite);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param sprite 
 * @return 
 */
    public breakout.view.SpriteView createSpriteView(breakout.model.Sprite sprite) {        
        if (sprite instanceof breakout.model.Field) {
            return createFieldView((breakout.model.Field) sprite);
        } else if (sprite instanceof breakout.model.Ball) {
            return createBallView((breakout.model.Ball) sprite);
        } else if (sprite instanceof breakout.model.Paddle) {
            return createPaddleView((breakout.model.Paddle) sprite);
        } else if (sprite instanceof breakout.model.Brick) {
            return createBrickView((breakout.model.Brick) sprite);
        } else {
            return null;
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param field 
 * @return 
 */
    public breakout.view.FieldView createFieldView(breakout.model.Field field) {        
        initSpriteView(new AWTFieldView(), field);
        ((AWTFieldView) view).init();
        return (breakout.view.FieldView) view;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param ball 
 * @return 
 */
    public breakout.view.BallView createBallView(breakout.model.Ball ball) {        
        initSpriteView(new AWTBallView(), ball);
        return (breakout.view.BallView) view;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param paddle 
 * @return 
 */
    public breakout.view.PaddleView createPaddleView(breakout.model.Paddle paddle) {        
        initSpriteView(new AWTPaddleView(), paddle);
        return (breakout.view.PaddleView) view;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param brick 
 * @return 
 */
    public breakout.view.BrickView createBrickView(breakout.model.Brick brick) {        
        initSpriteView(new AWTBrickView(), brick);
        return (breakout.view.BrickView) view;
    } 
 }
