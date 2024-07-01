package DAL;

import Domain.Obejects.TransportDocument;

import java.sql.SQLException;
import java.util.List;

public class TransportsDAO implements IDAO<TransportDocument>{
    @Override
    public TransportDocument get(int id) {
        return null;
    }
    @Override
    public List<TransportDocument> GET_ALL() {
        return null;
    }
    @Override
    public void INSERT(TransportDocument transportDocument) throws SQLException {

    }
    @Override
    public void UPDATE(TransportDocument transportDocument, String[] params) {

    }
    @Override
    public void DELETE(TransportDocument transportDocument) {

    }
}
