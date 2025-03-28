package Tutorial1;

public class Receiver {
        public void receiveMessage(Message message){
            System.out.println("Receiver:The message has been received");
            System.out.println("The message is :"+message.getContent());
        }
}
