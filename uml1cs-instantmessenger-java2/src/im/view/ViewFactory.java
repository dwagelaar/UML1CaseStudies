package im.view;


public abstract class ViewFactory {
public abstract im.view.ContactListView createContactListView();

public abstract im.view.NewContactDialog createNewContactDialog();

public abstract im.view.ConversationView createConversationView();

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

