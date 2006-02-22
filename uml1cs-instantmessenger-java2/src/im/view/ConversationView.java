package im.view;


public interface ConversationView {
public abstract java.lang.Object getContent();

public abstract void addContent(java.lang.Object c);

public abstract void addListener(im.view.ConversationViewListener l);

public abstract void setTitle(java.lang.String t);

public abstract void toFront();

}

