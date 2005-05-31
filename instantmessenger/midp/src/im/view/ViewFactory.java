
package im.view;

/**
 * <p></p>
 */
public abstract class ViewFactory {

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract im.view.ContactListView createContactListView();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract im.view.NewContactDialog createNewContactDialog();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public abstract im.view.ConversationView createConversationView();

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public static im.view.ViewFactory getDefault() {        
        String[] options = {
            "im.view.swing.SwingViewFactory",
            "im.view.awt.AWTViewFactory",
            "im.view.lcdui.LCDUIViewFactory"
        };
        ViewFactory factory = null;
        for (int i = 0; i < options.length && factory == null; i++) {
            try {
               factory = (ViewFactory) Class.forName(options[i]).newInstance();
            } catch (Exception e) {
            }
        }
        return factory;
    } 
 }
