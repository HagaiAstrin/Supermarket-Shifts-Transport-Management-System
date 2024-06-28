package Domain;


import com.google.gson.JsonObject;

public interface IRepository <T> {
    public void Add(T t);
    public void Delete(T t);
    public T FindByID(String ID);
    public JsonObject FindAll(String s, String v);
    public JsonObject FindMore();
    public int getAmount();
}
