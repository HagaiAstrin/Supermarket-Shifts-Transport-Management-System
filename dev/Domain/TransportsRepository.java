package Domain;

import java.util.ArrayList;

public class TransportsRepository implements IRepository<String>{

    private static ArrayList<String> allTransportations =  new ArrayList<>();

    @Override
    public void Add(String s) {
        allTransportations.add(s);
    }

    @Override
    public void Delete(String s) {
        allTransportations.remove(s);
    }

    @Override
    public String FindByIndex(int index) {
        return allTransportations.get(index);
    }

    @Override
    public String Find(String s) {
        for (String tran: allTransportations){
            if (tran.equals(s))
                return tran;
        }
        return null;
    }
}
