
package im.view;
/**
 * <p></p>
 */
public interface NewContactDialog {
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract String getUid();
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract String getName();
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract int getNetwork();
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract String getPassword();
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param l 
 */
    public abstract void addListener(im.view.NewContactDialogListener l);
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param n 
 */
    public abstract void addNetwork(String n);
}









