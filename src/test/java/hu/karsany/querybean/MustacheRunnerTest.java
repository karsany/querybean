package hu.karsany.querybean;


import hu.karsany.querybean.model.Pojo;
import hu.karsany.querybean.model.PojoField;
import hu.karsany.querybean.util.MustacheRunner;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MustacheRunnerTest {


    public static final String COMMENT = "This is a comment";
    public static final String PACKAGE_NAME = "hu.karsany.tesztpackage";
    private final static String CLASS_NAME = "ExampleClass";

    @Test
    public void pojoMustacheTest() {


        Pojo pojo = new Pojo();
        pojo.setClassName(CLASS_NAME);
        pojo.setComment(COMMENT);
        pojo.setPackageName(PACKAGE_NAME);
        pojo.setFields(new ArrayList<>());

        PojoField f1 = new PojoField("field1", "String", false);
        pojo.getFields().add(f1);

        PojoField f2 = new PojoField("field2", "String", true);
        pojo.getFields().add(f2);

        pojo.setImports(new ArrayList<String>());
        pojo.getImports().add("java.math.BigDecimal");

        String s = MustacheRunner.build("pojo.mustache", pojo);

        System.out.println(s);

        Assert.assertTrue(s.contains(CLASS_NAME));
        Assert.assertTrue(s.contains(COMMENT));
        Assert.assertTrue(s.contains("package " + PACKAGE_NAME));
        Assert.assertTrue(s.contains("getField1"));
        Assert.assertTrue(s.contains("setField1"));
        Assert.assertTrue(s.contains("getField2"));
        Assert.assertFalse(s.contains("setField2"));

    }
}