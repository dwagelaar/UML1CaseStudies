
package breakout.view.awt;

/**
 * <p></p>
 */
public class AWTBrick extends java.awt.Panel {

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void removeFromParent() {        
        if (getParent() != null) {
            getParent().remove(this);
        }
    } 
 }
