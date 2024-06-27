package DAL;
import java.sql.SQLException;
import java.util.*;

public interface DAO <T> {

    Optional<T> get(int id);

    List<T> getAll();

    void save(T t) throws SQLException;

    void update(T t, String[] params);

    void delete(T t);
}

