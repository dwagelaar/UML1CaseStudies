
package breakout.view.awt;

/**
 * <p></p>
 */
public class AWTPaddle extends java.awt.Button {

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
