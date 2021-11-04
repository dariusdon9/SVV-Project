package src;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static src.Server.verbose;
import static src.Server.defaultfileault;
import static src.Server.not_found;
import static src.Server.not_supported;
import static src.Server.connect;
public class Test2 {
    private String string;

    @Test
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
    @Test
    public void verifyBoolean(){
        assertNotEquals(verbose,false);
    }

    
}