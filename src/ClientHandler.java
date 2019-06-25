import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    Socket socket;
    ClientObj clientObj;
    public ClientHandler (Socket socket1){
        socket = socket1;
    }
    @Override
    public void run() {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
            clientObj = (ClientObj) objectInputStream.readObject();
//            Thread.sleep(5000);
            DataOutputStream printWriter = new DataOutputStream(socket.getOutputStream());
            printWriter.writeUTF("Server : GOT IT!" + clientObj.thisUser.name);
            System.out.println(clientObj.thisUser.name);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
