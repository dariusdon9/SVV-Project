package src;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class Server implements Runnable{

    public final File root = new File("com/example/webserver");
    static final String defaultfileault = "com/example/webserver/index.html";
    public static String not_found = "com/example/webserver/404.html";
    static final String not_supported = "com/example/webserver/not_supported.html";
    static final String string404 = "404 File Not Found";
    public static final int PORT = 8080;

    // verbose mode
    public static final boolean verbose = true;
    // Client Connection via Socket Class

    public static Socket connect;

    public Server(Socket c) throws IOException {
        connect = c;
    }

    public boolean stop() throws IOException {
        connect.close();
        return false;
    }

    public BufferedReader input;
    public PrintWriter output;
    public String request;
    public BufferedOutputStream dataOutput;

    public String in;
    public StringTokenizer parse;
    public String method;

    @Override
    public void run() {
        // particular connection
        //100% coverage on the input,output,request,dataOutput variables

        input = null;
        output = null;
        request = null;
        dataOutput = null;

        // port to listen connection
        try {

            input = new BufferedReader(new InputStreamReader(connect.getInputStream()));

            output = new PrintWriter(connect.getOutputStream());

            dataOutput = new BufferedOutputStream(connect.getOutputStream());

            // first line of client request
            in = input.readLine();
            // request parsing with string tokenizer
            parse = new StringTokenizer(in);
            //http method of the client
            method = parse.nextToken().toUpperCase();
            // requested file
            request = parse.nextToken().toLowerCase();
            // we check only get and head methods

            if (!method.equals("GET")  &&  !method.equals("HEAD")) {
                if (verbose) {
                    System.out.println("501 Not Implemented : " + method + " method.");
                }

                File file = new File(root, not_supported);
                int fileLength = (int) file.length();
                String content_type = "text/html";
                //read content to return to client
                byte[] fileData = readFileData(file, fileLength);
                System.out.println("HTTP/1.1 501 Not Implemented");
                System.out.println("Date: " + new Date());
                System.out.println("Content type: " + content_type);
                System.out.println("Content length: " + fileLength);
                System.out.println(); // blank line between headers and content, very important !
                System.out.flush(); // flush character output stream buffer
                // file
                dataOutput.write(fileData, 0, fileLength);
                dataOutput.flush();

            } else {
                // GET or HEAD method
                if (request.endsWith("/")) {
                    request += defaultfileault;
                }

                File file = new File(root, request);
                int fileLength = (int) file.length();
                String content = getContentType(request);

                if (method.equals("GET")) { // GET method so we return content
                    byte[] fileData = readFileData(file, fileLength);

                    // send HTTP Headers
                    System.out.println("HTTP/1.1 200 OK");
                    System.out.println("Date: " + new Date());
                    System.out.println("Content-type: " + content);
                    System.out.println("Content-length: " + fileLength);
                    System.out.println();
                    System.out.flush();

                    dataOutput.write(fileData, 0, fileLength);
                    dataOutput.flush();
                }

                if (verbose) {
                    out.println("File " + request + " of type " + content + " returned");
                }

            }



        } catch (FileNotFoundException fnfe) {
            try {
                assert output != null;
                assert dataOutput != null;
                fileNotFound(output, dataOutput, request);
            } catch (IOException ioe) {
                System.err.println("Error with file not found exception : " + ioe.getMessage());
            }

        } catch (IOException ioe) {
            System.err.println("Server error : " + ioe);
        } finally {
            try {
                assert input != null;
                input.close();
                out.close();
                assert dataOutput != null;
                dataOutput.close();
                connect.close(); // we close socket connection
            } catch (Exception e) {
                System.err.println("Error closing stream : " + e.getMessage());
            }

            if (verbose) {
                System.out.println("Connection closed.\n");
                try {
                    stop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public FileInputStream fileIn;
    public byte[] fileData;

    private byte[] readFileData(File file, int fileLength) throws IOException {
        fileIn = null;
        fileData = new byte[fileLength];
        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null)
                fileIn.close();
        }
        return fileData;
    }

    // return supported MIME Types

    //tested both branches
    //100 line coverage
    public String getContentType(String request) {
        if (request.endsWith(".htm")  ||  request.endsWith(".html"))
            return "text/html";
        else
            return "text/plain";
    }

    public String content;
    public File file = new File(root, not_found);

     public void fileNotFound(PrintWriter output, OutputStream dataOut, String request) throws IOException {
        int fileLength = (int) file.length();
        content = "text/html";

        byte[] fileData = readFileData(file, fileLength);


        System.out.println(string404);
        System.out.println("Date: " + new Date());
        System.out.println("Content-type: " + content);
        System.out.println("Content-length: " + fileLength);


        System.out.println(); // blank line between headers and content, very important !
        System.out.flush(); // flush character output stream buffer

        dataOut.write(fileData, 0, fileLength);
        dataOut.flush();
        output.println("Found");

        if (verbose) {
            System.out.println("File " + request + " not found");
        }
    }
}

