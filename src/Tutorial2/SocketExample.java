package Tutorial2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketExample {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",5555);

        DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());

        String msg=dataInputStream.readUTF();
    }

}
