package Repository;

import Domain.Identifiable;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public abstract class DBRepository<T extends Identifiable<U>,U> extends MemoryRepository<T,U> {
    protected final String URL = "jdbc:sqlite:D:/An2/MAP/Project4/lib/Data.db";
    protected String tableName;
    protected Connection conn = null;

    public DBRepository(String tableName) {
        this.tableName = tableName;
    }

    public abstract void getData();

    protected void openConnection() throws SQLException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(URL);
        if (conn == null || conn.isClosed())
            conn = dataSource.getConnection();
    }

    public void closeConnection() throws SQLException {
       conn.close();
    }
}