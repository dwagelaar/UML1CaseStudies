
package im.edit;

/**
 * <p></p>
 */
public class ConversationEdit implements observer.Observer, im.view.ConversationViewListener {

/**
 * <p>Represents ...</p>
 */
    private im.model.Conversation model = null;

/**
 * <p>Represents ...</p>
 */
    private im.view.ConversationView view = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param e 
 */
    public void update(java.util.Hashtable e) {        
        String name = (String) e.get("name");
        if (name.equals("Message")) {
            onMessageChange((im.model.messages.Message) e.get("value"));
        }
        if (name.equals("Contact")) {
            onContactChange((im.model.Contact) e.get("value"));
        }
        if (name.equals("Factory")) {
            onFactoryChange((im.model.messages.MessageFactory) e.get("value"));
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param view 
 */
    public void setView(im.view.ConversationView view) {        
        this.view = view;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param model 
 */
    public void setModel(im.model.Conversation model) {        
        // Begin subscribe stanza
        if (this.model != null) this.model.deleteObserver(this);
        // Begin original body
        this.model = model;
        // End original body
        if (model != null) model.addObserver(this);
        // End subscribe stanza
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.view.ConversationView getView() {        
        return view;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @return 
 */
    public im.model.Conversation getModel() {        
        return model;
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public  ConversationEdit(im.model.Conversation c) {        
        setModel(c);
        im.InstantMessagingClient client = im.InstantMessagingClient.getInstance();
        setView(client.getViewFactory().createConversationView());
        getView().addListener(this);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param m 
 */
    public void onMessageChange(im.model.messages.Message m) {        
        if (getModel().getMessages().contains(m)) {
            if (m.getContent() instanceof String) {
                im.model.ContactList list =
                    im.InstantMessagingClient.getInstance().getContactList();
                getView().addContent(list.getUserName(m.getSender())
                    + ": " + m.getContent());
            } else {
                getView().addContent(m.getContent());
            }
            getView().toFront();
        }
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param c 
 */
    public void onContactChange(im.model.Contact c) {        
        getView().setTitle(getModel().getContact().getName());
        getView().toFront();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param f 
 */
    public void onFactoryChange(im.model.messages.MessageFactory f) {        
        if (f.isValidContent(new String())) {
            getView().setTextEnabled(true);
        } else {
            getView().setTextEnabled(false);
        }
        getView().toFront();
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onConversationClose() {        
        im.InstantMessagingClient.getInstance().removeConversation(getModel());
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 */
    public void onConversationSend() {        
        im.model.Contact recipient = getModel().getContact();
        im.model.Contact sender = getSender(recipient);
        im.model.messages.Message msg = getModel().getFactory().createMessage();
        msg.setNetwork(recipient.getNetwork());
        msg.setSender(sender.getUserId());
        msg.setRecipient(recipient.getUserId());
        msg.setContent(getView().getContent());
        msg.send();
        getModel().addMessage(msg);
    } 

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param recipient 
 * @return 
 */
    private im.model.Contact getSender(im.model.Contact recipient) {        
        im.InstantMessagingClient c = im.InstantMessagingClient.getInstance();
        return c.getContactList().getIdentity(recipient.getNetwork());
    } 
 }
