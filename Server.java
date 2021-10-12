import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(264);
        Socket socket = serverSocket.accept();
        System.out.println("Client Connected");

        InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStream);
        String string = bufferedReader.readLine();
        System.out.println("Client: " + string);

    }
}