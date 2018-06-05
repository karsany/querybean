package hu.karsany.querybean.configmodel;

import com.thoughtworks.xstream.XStream;

public class QuerybeanXStreamBuilder {

    private final XStream xStream = new XStream();
    private final String xml;

    public QuerybeanXStreamBuilder(String xml) {
        this.xml = xml;

        xStream.alias("querybean", QueryBean.class);
        xStream.alias("connection", Connection.class);
        xStream.alias("bean", Bean.class);
        xStream.aliasField("class", Bean.class, "clazz");
        xStream.aliasField("package", Bean.class, "packageName");

    }


    public QueryBean toQueryBean() {
        return (QueryBean) xStream.fromXML(this.xml);
    }

    public String getXml() {
        return xml;
    }

    public XStream getxStream() {
        return xStream;
    }
}
