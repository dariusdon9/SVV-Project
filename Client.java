import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException{
        Socket client_socket = new Socket("localhost", 264);
        new JOptionPane();
        String temp = JOptionPane.showInputDialog("Write Your message");
        PrintWriter printWriter = new PrintWriter(client_socket.getOutputStream());
        printWriter.println(temp);
        printWriter.flush();
    }
}
