package DAL;
import java.sql.SQLException;
import java.util.*;

public interface IDAO<T> {

     T get(int id);
    List<T> GET_ALL();
    void INSERT(T t) throws SQLException;
    void UPDATE(T t, String[] params);
    void DELETE(T t);
}

