package im.view.awt;


public class AWTViewFactory extends im.view.ViewFactory {
public im.view.ContactListView createContactListView() {
        return new AWTContactListView();

}

public im.view.NewContactDialog createNewContactDialog() {
        return new AWTNewContactDialog();

}

public im.view.ConversationView createConversationView() {
        return new AWTConversationView();

}

public AWTViewFactory() throws java.lang.ClassNotFoundException {
        // fail factory creation if AWT not available
        Class.forName("java.awt.Toolkit");

}

}

