package im.networking.local;


public class Local extends im.networking.Network implements im.ExceptionReporter {
public Local() {
        setName("Local");

}

public void report(Exception e) {
        im.model.Message msg = new im.model.Message();
        msg.setSender("System");
        msg.setContent(e.toString());
        send(msg);
        e.printStackTrace();

}

public void send(im.model.Message msg) {
        msg.setRecipient(msg.getSender());
        setRecvMsg(msg);

}

}

