
package breakout;
import breakout.view.awt.*;

/**
 * <p></p>
 */
public class BreakOut {

/**
 * <p>Represents ...</p>
 */
    private breakout.view.ViewFactory viewFactory = null;

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public  BreakOut() {        
        viewFactory = new breakout.view.awt.AWTViewFactory();
        viewFactory.createFieldView(new breakout.model.Field());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param viewFactory 
 */
    public void setViewFactory(breakout.view.ViewFactory viewFactory) {        
        this.viewFactory = viewFactory;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public breakout.view.ViewFactory getViewFactory() {        
        return viewFactory;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param args 
 */
    public static void main(String[] args) {        
        new BreakOut();
    } 
 }
