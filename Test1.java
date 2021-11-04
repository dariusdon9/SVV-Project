package src;

import com.github.fge.jsonschema.core.exceptions.ExceptionProvider;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockserver.integration.ClientAndServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Semaphore;

class Test1 {


    private static final int PORT = 8080;
    private OutputStream serverOut;
    private InputStream serverIn;
    ServerSocket server = new ServerSocket(PORT);
    private Semaphore lock = new Semaphore(0);

    Test1() throws IOException {
    }

    @Test
    public void testClientServer() throws IOException, InterruptedException {
        listen(server);

        Socket client = new Socket("localhost", PORT);

        OutputStream clientOut = client.getOutputStream();
        InputStream clientIn = client.getInputStream();

        System.out.println("Wait");
        lock.acquire();
        System.out.println("Lock Done");
    }

    private void write(OutputStream out, String string) throws IOException {
        out.write(string.getBytes());
        out.flush();
    }

    private void printWrite(OutputStream out, String string) throws IOException {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.print(string);
    }

    private void reading(InputStream in,String message) throws IOException{
        Assertions.assertEquals(Float.parseFloat("Wrong bytes dimension:"), message.length(),in.available());
                byte[] buff = new byte[message.length()];
        in.read(buff);
        Assertions.assertEquals(message,new String(buff));
    }
    @BeforeEach
    void setUp() throws IOException {
            Socket socket = new Socket();
            socket.getLocalPort();
            socket.getLocalAddress();
            socket.connect(server.getLocalSocketAddress());
            server.accept();
    }

    @AfterEach
     void tearDown() throws IOException{
        Socket socket = new Socket();
        server.close();
        socket.close();
    }
    private void listen(ServerSocket server){
        new Thread(()->{
            try {
                Socket socket = server.accept();
                System.out.println(socket);

                serverOut = socket.getOutputStream();
                serverIn = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
