package hu.karsany.querybean.configmodel;

import com.thoughtworks.xstream.XStream;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class QueryBeanXStreamBuilderTest {

    public static final String XML = "<querybean>\n" +
            "\t<beans>\n" +
            "\t\t<bean>\n" +
            "\t\t\t<class>Dual</class>\n" +
            "\t\t\t<package>hu.karsany.querybean.test</package>\n" +
            "\t\t\t<connection>tstOra</connection>\n" +
            "\t\t\t<query>\n" +
            "\t\t\t\tselect t.*, 1 as num_col, to_date('99991231','yyyymmdd') dtcol from dual t\n" +
            "\t\t\t</query>\n" +
            "\t\t</bean>\n" +
            "\t\t\n" +
            "\t\t<bean>\n" +
            "\t\t\t<class>UserTables</class>\n" +
            "\t\t\t<package>hu.karsany.querybean.test</package>\n" +
            "\t\t\t<connection>tstOra2</connection>\n" +
            "\t\t\t<query>\n" +
            "\t\t\t\tselect * from USER_TABLES\n" +
            "\t\t\t</query>\n" +
            "\t\t</bean>\n" +
            "\n" +
            "\t\t<bean>\n" +
            "\t\t\t<class>InformationSchemaTable</class>\n" +
            "\t\t\t<package>hu.karsany.querybean.test</package>\n" +
            "\t\t\t<connection>tstH2</connection>\n" +
            "\t\t\t<query>\n" +
            "\t\t\t\tselect * from information_Schema.tables\n" +
            "\t\t\t</query>\n" +
            "\t\t</bean>\n" +
            "\t</beans>\t\n" +
            "\t\n" +
            "\t<connections>\n" +
            "\t\t<connection>\n" +
            "\t\t\t<name>tstOra</name>\n" +
            "\t\t\t<url>jdbc:oracle:thin:obridge/obridge@localhost:1521:xe</url>\n" +
            "\t\t</connection>\n" +
            "\t\t<connection>\n" +
            "\t\t\t<name>tstOra2</name>\n" +
            "\t\t\t<url>jdbc:oracle:thin:localhost:1521:xe</url>\n" +
            "\t\t\t<user>hr</user>\n" +
            "\t\t\t<password>hr</password>\n" +
            "\t\t</connection>\n" +
            "\t\t<connection>\n" +
            "\t\t\t<name>tstH2</name>\n" +
            "\t\t\t<url>jdbc:h2:mem:test_mem</url>\n" +
            "\t\t</connection>\t\t\n" +
            "\t</connections>\n" +
            "\t\n" +
            "</querybean>";

    @Test
    public void getxStream() {

        XStream xStream = new QuerybeanXStreamBuilder("empty...").getxStream();

        QueryBean qb = new QueryBean();

        ArrayList<Connection> cs = new ArrayList<>();
        cs.add(new Connection("c1", "url"));
        qb.setConnections(cs);

        ArrayList<Bean> bs = new ArrayList<>();
        Bean b = new Bean();
        b.setClazz("Dual");
        b.setConnection("c1");
        b.setPackageName("hello");
        b.setQuery("select * from dual");
        bs.add(b);
        qb.setBeans(bs);

        String xml = xStream.toXML(qb);

        System.out.println(xml);

        Assert.assertTrue(xml.startsWith("<querybean"));
        Assert.assertTrue(xml.contains("<connection>"));
        Assert.assertTrue(xml.contains("<bean>"));
        Assert.assertFalse(xml.contains("<clazz>"));


    }

    @Test
    public void testBuildQB() {
        QueryBean queryBean = new QuerybeanXStreamBuilder(XML).toQueryBean();

        Assert.assertEquals(3, queryBean.getBeans().size());
        Assert.assertEquals(3, queryBean.getConnections().size());
    }
}