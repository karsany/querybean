package hu.karsany.querybean.configmodel;

import java.util.List;

public class QueryBean {

    private List<Bean> beans;
    private List<Connection> connections;

    public List<Bean> getBeans() {
        return beans;
    }

    public void setBeans(List<Bean> beans) {
        this.beans = beans;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }


}
