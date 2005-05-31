
package im.edit;

/**
 * <p></p>
 */
public class ConversationEdit implements observer.Observer, im.view.ConversationViewListener {

/**
 * <p>Represents ...</p>
 */
    private im.view.ConversationView view = null;

/**
 * <p>Represents ...</p>
 */
    private im.model.Conversation model = null;

/**
 * <p>Does ...</p>
 * 
 * 
 * 
 * @param name 
 * @param value 
 */
    public void update(String name, Object value) {        
        if (name.equals("Message")) {
            onMessageChange((im.model.Message) value);
        }
        if (name.equals("Contact")) {
            onContactChange((im.model.Contact) value);
        }
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
    public void onMessageChange(im.model.Message m) {        
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
        im.model.Message msg = new im.model.Message();
        msg.setNetwork(recipient.getNetwork());
        msg.setSender(sender.getUserId());
        msg.setRecipient(recipient.getUserId());
        msg.setContent(getView().getContent());
        msg.send();
        getModel().setMessage(msg);
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
