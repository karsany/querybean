package hu.karsany.querybean;

import hu.karsany.querybean.model.Pojo;
import hu.karsany.querybean.model.PojoField;
import hu.karsany.querybean.util.MustacheRunner;
import hu.karsany.querybean.util.StringHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeanBuilder {
    public static String fromQuery(String query, String className, String packageName, Connection connection) throws SQLException {

        CallableStatement callableStatement = connection.prepareCall("select * from (" + query + ") where 1=2");
        ResultSet resultSet = callableStatement.executeQuery();

        ResultSetMetaData md = resultSet.getMetaData();

        Pojo pc = new Pojo();

        pc.setClassName(className);
        pc.setPackageName(packageName);
        pc.setComment(query);
        pc.setGeneratorName("hu.karsany.querybean");

        List<String> imps = new ArrayList<>();
        imps.add("javax.annotation.Generated");
        pc.setImports(imps);

        for (int i = 1; i <= md.getColumnCount(); i++) {
            pc.getFields().add(new PojoField(StringHelper.toCamelCaseSmallBegin(md.getColumnName(i)), md.getColumnClassName(i), false));
        }

        resultSet.close();
        callableStatement.close();
        connection.close();

        return MustacheRunner.build("pojo.mustache", pc);
    }
}
