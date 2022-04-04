package repository;

import java.sql.Connection;

public interface DBConnection {

    Connection connect(String dbname, String user, String password);

    void createTable(String name);

    void insert(String tableName, String city,String cityid);

    void read(String tableName);

    public void update(int id, String tableName, String newValue, String oldValue);

    public void delete(String city);

    void searchByCity(String city);

    public String getKeyById(String id);
}
