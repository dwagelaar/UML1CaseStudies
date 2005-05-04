
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
    public abstract im.networking.Network getNetwork();
/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param l 
 */
    public abstract void addListener(im.view.NewContactDialogListener l);
}









