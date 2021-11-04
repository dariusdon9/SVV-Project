package src;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Test3{
    private void print(String out){
        System.out.println(out);
    }

    private final PrintStream standardout = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void givenSystemOut(){
        print("Hello");
        Assert.assertEquals("Hello",outputStream.toString().trim());
    }

    @BeforeEach
    public void tearDown(){
        System.setOut(standardout);
    }
}
