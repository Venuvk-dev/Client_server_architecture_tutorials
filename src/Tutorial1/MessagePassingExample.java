package Tutorial1;

public class MessagePassingExample {
    public static void main(String[] args) {
        Sender sender=new Sender();
        Receiver receiver=new Receiver();

//        Message message=new Message("Hello This is Venukanth");
        sender.sendMessage(new Message("Hello This is from Venukanth"),receiver);
    }
}
