package com.example.webserver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class Server implements Runnable{

    static final File root = new File(".");
    static final String defaultfileault = "index.html";
    static final String not_found = "404.html";
    static final String not_supported = "not_supported.html";
    // port to listen connection
    static final int PORT = 8080;

    // verbose mode
    static final boolean verbose = true;

    // Client Connection via Socket Class
    private final Socket connect;

    public Server(Socket c) {
        connect = c;
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverConnect = new ServerSocket(PORT);
            out.println("Server started......\n");
            out.println("Connections on port : " + PORT + " ...\n");                    

            // lsiten server execution
            while (true) {
                Server server = new Server(serverConnect.accept());

                if (verbose) {
                    out.println("Connecton opened. (" + new Date() + ")");
                }
                //dedicated thread
                Thread thread = new Thread(server);
                thread.start();
            }

        } catch (IOException e) {
            System.err.println("Server Connection error : " + e.getMessage());
        }
    }

    @Override
    public void run() {
        // particular connection
        BufferedReader input = null;
        PrintWriter output = null;
        String request = null;
        BufferedOutputStream dataOutput = null;

        try {
          
            input = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            
            output = new PrintWriter(connect.getOutputStream());
            
            dataOutput = new BufferedOutputStream(connect.getOutputStream());

            // first line of client request
            String in = input.readLine();
            // request parsing with string tokenizer
            StringTokenizer parse = new StringTokenizer(in);
            //http method of the client
            String method = parse.nextToken().toUpperCase(); 
            // requested file
            request = parse.nextToken().toLowerCase();

            // we check only get and head methods
            if (!method.equals("GET")  &&  !method.equals("HEAD")) {
                if (verbose) {
                    out.println("501 Not Implemented : " + method + " method.");
                }

                
                File file = new File(root, not_supported);
                int fileLength = (int) file.length();
                String content_type = "text/html";
                //read content to return to client
                byte[] fileData = readFileData(file, fileLength);
                
                out.println("HTTP/1.1 501 Not Implemented");
                out.println("Date: " + new Date());
                out.println("Content type: " + content_type);
                out.println("Content length: " + fileLength);
                out.println(); // blank line between headers and content, very important !
                out.flush(); // flush character output stream buffer
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
                    out.println("HTTP/1.1 200 OK");
                    out.println("Date: " + new Date());
                    out.println("Content-type: " + content);
                    out.println("Content-length: " + fileLength);
                    out.println();
                    out.flush(); 

                    dataOutput.write(fileData, 0, fileLength);
                    dataOutput.flush();
                }

                if (verbose) {
                    out.println("File " + request + " of type " + content + " returned");
                }

            }

        } catch (FileNotFoundException fnfe) {
            try {
                fileNotFound(output, dataOutput, request);
            } catch (IOException ioe) {
                System.err.println("Error with file not found exception : " + ioe.getMessage());
            }

        } catch (IOException ioe) {
            System.err.println("Server error : " + ioe);
        } finally {
            try {
                input.close();
                out.close();
                dataOutput.close();
                connect.close(); // we close socket connection
            } catch (Exception e) {
                System.err.println("Error closing stream : " + e.getMessage());
            }

            if (verbose) {
                out.println("Connection closed.\n");
            }
        }


    }

    private byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];

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
    private String getContentType(String request) {
        if (request.endsWith(".htm")  ||  request.endsWith(".html"))
            return "text/html";
        else
            return "text/plain";
    }

    private void fileNotFound(PrintWriter out, OutputStream dataOut, String request) throws IOException {
        File file = new File(root, not_found);
        int fileLength = (int) file.length();
        String content = "text/html";
        byte[] fileData = readFileData(file, fileLength);

        out.println("404 File Not Found");
        out.println("Date: " + new Date());
        out.println("Content-type: " + content);
        out.println("Content-length: " + fileLength);
        out.println(); // blank line between headers and content, very important !
        out.flush(); // flush character output stream buffer

        dataOut.write(fileData, 0, fileLength);
        dataOut.flush();

        if (verbose) {
            System.out.println("File " + request + " not found");
        }
    }

}
