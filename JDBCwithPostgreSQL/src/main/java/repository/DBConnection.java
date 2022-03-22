package repository;

import java.sql.Connection;

public interface DBConnection {

    Connection connect(String dbname, String user, String password);

    void createTable(Connection con, String name);

    void insert(Connection con, String tableName, String city, String country, String cityid);

    void read(Connection con, String tableName);

    void update(Connection con, String tableName, String colomnName, String newValue, String oldValue);

    void delete();

    void searchByCity(Connection con, String city);
}
