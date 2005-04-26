
package breakout.model;

/**
 * <p></p>
 */
public class Ball extends breakout.model.Sprite {

/**
 * <p>Represents ...</p>
 */
    private boolean running = false;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param running 
 */
    public void setRunning(boolean running) {        
        // Begin Observable stanza
        if (this.running != running) {
            // Begin original body
        this.running = running;
            // End original body
            setChanged();
            java.util.Hashtable e = new java.util.Hashtable();
            e.put("name", "Running");
            e.put("class", Boolean.class);
            e.put("value", new Boolean(running));
            notifyObservers(e);
        }
        // End Observable stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public boolean getRunning() {        
        return running;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void run() {        
        while (running) {
        }
    } 
 }
