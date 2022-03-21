package repository;

import java.sql.Connection;

public interface DBConnection {

    Connection connect(String dbname, String user, String password);

    void createTable(Connection con,String name);

    void insert(Connection con, String tableName, String city, String country, int cityid);

    void read();

    void update();

    void delete();
}
