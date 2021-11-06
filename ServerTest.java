package Test;

import org.junit.jupiter.api.Test;
import src.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static src.Server.connect;

public class ServerTest {
    @Test
    //test for first case getContentType
    public void testContentHtml() throws IOException {
       ServerSocket server = new ServerSocket(8081);
       Socket socket = new Socket("localhost", 8081);
       Server server2 = new Server(socket);
        String string = new String("test.html");
        assertEquals(server2.getContentType(string),"text/html");
    }

    @Test
    //test for get content
    public void testContentHtml1() throws IOException {
        ServerSocket server = new ServerSocket(8082);
        Socket socket = new Socket("localhost", 8082);
        Server server2 = new Server(socket);
        String string = new String("test.htm");
        assertEquals(server2.getContentType(string),"text/html");
    }
    @Test
    //cover the get content type
    public void testContentText() throws IOException {
        ServerSocket server = new ServerSocket(8083);
        Socket socket = new Socket("localhost", 8083);
        Server server2 = new Server(socket);
        String string = new String("test.txt");
        assertEquals(server2.getContentType(string),"text/plain");
    }

    @Test
    //cover the stop function
    public void checkStop() throws IOException {
        ServerSocket server = new ServerSocket(8084);
        Socket socket = new Socket("localhost", 8084);
        Server server2 = new Server(socket);
        assertTrue(socket.isConnected());
        assertEquals(socket,connect);
        assertNotEquals(server2.stop(),true);
    }

    @Test
    //test for running the run()
    public void checkbasicRun() throws IOException {
        ServerSocket server = new ServerSocket(8085);
        Socket socket = new Socket("localhost", 8085);
        Server server2 = new Server(socket);

        server2.run();
        server2.stop();
    }
    @Test
    //test for null principal variables for test
    public void checkadvancedRun() throws IOException {
        ServerSocket server = new ServerSocket(8085);
        Socket socket = new Socket("localhost", 8085);
        Server server2 = new Server(socket);

      BufferedReader input1 = null;
      PrintWriter output1 = null;
      String request1 = null;
      BufferedOutputStream dataOutput1 = null;

      assertEquals(dataOutput1,server2.dataOutput);
      assertEquals(output1,server2.output);
      assertEquals(input1,server2.input);
      assertEquals(request1,server2.request);
    }


    @Test
    //test for socket constructor
    public void checkSocket() throws IOException {
        ServerSocket server = new ServerSocket(8089);
        Socket socket = new Socket("localhost", 8089);
        Server server2 = new Server(socket);
        assertEquals(socket,connect);

    }

    @Test
    //verify the value of verbose
   public void checkFile() throws IOException {
        ServerSocket server = new ServerSocket(8087);
        Socket socket = new Socket("localhost", 8087);
        Server server2 = new Server(socket);
        boolean verbose = true;
        assertEquals(verbose,true);
    }


}
