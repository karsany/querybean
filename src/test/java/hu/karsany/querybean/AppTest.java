package hu.karsany.querybean;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class AppTest {

    @Test
    public void test1() throws IOException, SQLException {
        App.main();
    }

    @Test
    public void test2() throws IOException, SQLException {
        App.main("-d", "src\\test\\resources\\example.xml", "-o", ".\\~test\\");
    }

    @Test
    public void regexpreplace() {
        System.out.println("hello.bello.szia".replace(".", "\\"));
    }


}