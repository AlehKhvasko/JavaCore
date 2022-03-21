package repository;

import java.sql.Connection;

public interface DBConnection {

    Connection connect(String dbname, String user, String password);

    void createTable(Connection con,String name);

    void insert(Connection con, String tableName, String city, String country, String cityid);

    public void read(Connection con, String tableName);

    void update();

    void delete();
}
