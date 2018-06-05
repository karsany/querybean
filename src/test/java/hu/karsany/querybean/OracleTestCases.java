package hu.karsany.querybean;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleTestCases {

    @Test
    public void testQueryBean() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:obridge/obridge@localhost:1521:xe");

        String resp = BeanBuilder.fromQuery("select * from dual", "Dual", "hu.test.test", connection);
        Assert.assertTrue(resp.contains("class Dual"));
        Assert.assertTrue(resp.contains("private java.lang.String dummy"));

        System.out.println(resp);

    }

    @Test
    public void testQueryBean2() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:obridge/obridge@localhost:1521:xe");

        String resp = BeanBuilder.fromQuery("select t.*, 1 as num_col from dual t", "Dual", "hu.test.test", connection);
        System.out.println(resp);
        Assert.assertTrue(resp.contains("class Dual"));
        Assert.assertTrue(resp.contains("private java.lang.String dummy"));
        Assert.assertTrue(resp.contains("private java.math.BigDecimal numCol"));


    }

    @Test
    public void testQueryBean3() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:obridge/obridge@localhost:1521:xe");

        String resp = BeanBuilder.fromQuery("select t.*, 1 as num_col, to_date('99991231','yyyymmdd') dtcol from dual t", "Dual", "hu.test.test", connection);
        System.out.println(resp);
        Assert.assertTrue(resp.contains("class Dual"));
        Assert.assertTrue(resp.contains("private java.lang.String dummy"));
        Assert.assertTrue(resp.contains("private java.math.BigDecimal numCol"));
        Assert.assertTrue(resp.contains("private java.sql.Timestamp dtcol"));


    }

}
