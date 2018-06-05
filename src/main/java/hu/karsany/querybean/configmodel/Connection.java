package hu.karsany.querybean.configmodel;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private String name;
    private String url;
    private String user = null;
    private String password = null;

    public Connection(String name, String url, String user, String password) {
        this.name = name;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection(String name, String url) {

        this.name = name;
        this.url = url;
    }

    public Connection() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public java.sql.Connection getSqlConnection() throws SQLException {
        if (this.user != null && this.password != null) {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } else {
            return DriverManager.getConnection(this.url);
        }
    }
}
