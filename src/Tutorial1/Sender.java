package Tutorial1;


public class Sender {
    public Message createMessage(String content){
        Message message=new Message(content);
        System.out.println("The Message has been created :"+content);
        return message;
    }

    public void sendMessage(Message message, Receiver receiver){
        System.out.println("The message is being sent to the receiver");
        receiver.receiveMessage(message);
    }


}
