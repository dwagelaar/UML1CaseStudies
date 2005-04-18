
package breakout.view.awt;

/**
 * <p></p>
 */
public class AWTBall extends java.awt.Component {

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param g 
 */
    public void paint(java.awt.Graphics g) {        
        java.awt.Point pos = getLocation();
        java.awt.Dimension size = getSize();
        g.fillOval(pos.x, pos.y, size.width, size.height);
    } 

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
