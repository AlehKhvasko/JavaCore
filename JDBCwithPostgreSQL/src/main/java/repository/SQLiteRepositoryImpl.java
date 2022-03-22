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
    public void insert(Connection con, String tableName, String city, String country, String cityid) {

    }


    @Override
    public void read(Connection con, String tableName) {

    }

    @Override
    public void update(Connection con, String tableName, String colomnName, String newValue, String oldValue) {

    }

    @Override
    public void delete() {

    }
}
