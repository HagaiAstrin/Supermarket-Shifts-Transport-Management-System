package DAL;

import Domain.Obejects.Site;

import java.sql.SQLException;
import java.util.List;

public class SitesDAO implements IDAO<Site>{
    @Override
    public Site get(int id) {
        return null;
    }
    @Override
    public List<Site> GET_ALL() {
        return null;
    }
    @Override
    public void INSERT(Site site) throws SQLException {

    }
    @Override
    public void UPDATE(Site site, String[] params) {

    }
    @Override
    public void DELETE(Site site) {

    }
}
