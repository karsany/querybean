package hu.karsany.querybean;

import com.beust.jcommander.JCommander;
import hu.karsany.querybean.configmodel.Bean;
import hu.karsany.querybean.configmodel.Connection;
import hu.karsany.querybean.configmodel.QueryBean;
import hu.karsany.querybean.configmodel.QuerybeanXStreamBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String... args) throws IOException, SQLException {

        Parameters ps = commandLineParsing(args);
        if (ps == null)
            return;

        String xml = FileUtils.readFileToString(ps.getQueryBeanDescriptor(), "utf8");

        QueryBean queryBean = new QuerybeanXStreamBuilder(xml).toQueryBean();

        Map<String, Connection> idcMap = new HashMap<>();

        for (Connection c : queryBean.getConnections()) {
            idcMap.put(c.getName(), c);
        }

        for (Bean b : queryBean.getBeans()) {
            String s = BeanBuilder.fromQuery(
                    b.getQuery(),
                    b.getClazz(),
                    b.getPackageName(),
                    idcMap.get(b.getConnection()).getSqlConnection()
            );

            File f = new File(ps.getOutputDirectory() + "\\" + b.getPackageName().replace(".", "\\") + "\\" + b.getClazz() + ".java");

            FileUtils.write(f, s, "utf8");

        }


    }

    private static Parameters commandLineParsing(String[] args) {
        // Command Line
        if (args.length == 0) {
            Parameters ps = new Parameters();
            JCommander.newBuilder()
                    .addObject(ps)
                    .programName("querybean")
                    .build()
                    .usage();

            return null;
        }

        Parameters ps = new Parameters();
        JCommander.newBuilder()
                .addObject(ps)
                .build()
                .parse(args);
        return ps;
    }

}
