package repository;

import java.sql.Connection;

public interface DBConnection {

    Connection connect(String dbname, String user, String password);

    void insert();

    void read();

    void update();

    void delete();
}
