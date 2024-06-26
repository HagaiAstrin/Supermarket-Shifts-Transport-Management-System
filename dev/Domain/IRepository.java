package Domain;

import java.util.Map;

public interface IRepository <T> {
    public void Add(T t);
    public void Delete(T t);
    public T FindByIndex(int index);
    public T Find(String s);

}
