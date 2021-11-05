package src;

import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import static org.junit.jupiter.api.Assertions.*;
import static src.Server.*;
import static src.Server.verbose;

class ServerTest {


    private static final int PORT = 8080;
    private OutputStream serverOut;
    private InputStream serverIn;
    ServerSocket server = new ServerSocket(PORT);
    private Semaphore lock = new Semaphore(0);

    ServerTest() throws IOException {
    }

    @Test
    public void testClientServer() throws IOException, InterruptedException {
        listen(server);

        Socket client = new Socket("localhost", PORT);

        OutputStream clientOut = client.getOutputStream();
        InputStream clientIn = client.getInputStream();
        Assertions.assertNotEquals(clientIn,null);//verify that inputstream has a value
        Assertions.assertEquals(clientOut,null);//verify that outputstram has a value

        System.out.println("Wait");
        lock.acquire();
        System.out.println("Lock Done");

        write(clientOut,"Hello babe!");
        reading(clientIn,"Hello babe!");
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
    private String string;

    @org.junit.jupiter.api.Test
    public void FindFile() {
        File file = new File("C:\\Users\\Darius\\Desktop\\Try_Web\\src\\src");
        assertTrue(file.exists());
        assertEquals(true, file.isDirectory());
    }

    public void verifyVariable( String string){

        if(string.equals("com/example/webserver/index.html")){
            System.out.println("Index Path Verified");
            assertTrue(string.equals(defaultfileault));
        }

        else if(string.equals("com/example/webserver/404.html")){
            System.out.println("404 Path Verified");
            assertTrue(string.equals(not_found));
        }

        else if(string.equals("com/example/webserver/not_supported.html")){
            System.out.println("Not_Suported Path Verified");
            assertTrue(string.equals(not_supported));
        }

        else{
            System.out.println("Wrong path");
        }
    }
    @org.junit.jupiter.api.Test
    public void verifyBoolean(){
        assertNotEquals(verbose,false);
    }
    private void print(String out){
        System.out.println(out);
    }

    private final PrintStream standardout = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp_out(){
        System.setOut(new PrintStream(outputStream));
    }

    @org.junit.jupiter.api.Test
    void givenSystemOut(){
        print("Hello");
        Assert.assertEquals("Hello",outputStream.toString().trim());
    }

    @BeforeEach
    public void tearDown_out(){
        System.setOut(standardout);
    }
}

