package repository;
import java.sql.Connection;

public class SQLiteRepositoryImpl implements DBConnection{

    @Override
    public Connection connect(String dbname, String user, String password) {
        return null;
    }

    @Override
    public void createTable(Connection con, String name) {

    }

    @Override
    public void insert(Connection con, String tableName, String city, String country, int cityid) {

    }

    @Override
    public void read() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
