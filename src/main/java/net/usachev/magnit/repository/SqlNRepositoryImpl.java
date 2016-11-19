package net.usachev.magnit.repository;

import net.usachev.magnit.sql.Sql;
import org.slf4j.Logger;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class SqlNRepositoryImpl implements NRepository {

    private Sql sql;

    public SqlNRepositoryImpl(String dbUrl, String dbUser, String dbPassword) {
        sql = new Sql(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }


    @Override
    public void createTable() {
        sql.execute("DROP TABLE IF EXISTS test; CREATE TABLE test (field int)");
    }

    @Override
    public void save(final int record) {
        sql.execute(connection -> {
            try (PreparedStatement st = connection.prepareStatement("INSERT INTO test (field) VALUES (?)")) {
                st.setInt(1, record);
                st.execute();
            }
            return null;
        });
    }

    @Override
    public List<Integer> getRecords() {
        return sql.execute("SELECT * FROM test",
                st -> {
                    ResultSet rs = st.executeQuery();
                    List<Integer> list = new ArrayList<>();
                    while (rs.next()) {
                        int record = rs.getInt("field");
                        list.add(record);
                    }
                    return list;
                });
    }
}
