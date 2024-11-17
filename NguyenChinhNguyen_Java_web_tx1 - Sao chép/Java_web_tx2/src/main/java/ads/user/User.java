package ads.user;

import ads.objects.UserObject;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface User {
    boolean addUser(UserObject user);
    boolean editUser(UserObject user);
    boolean delUser(UserObject user);
    ArrayList<ResultSet> getUsers(String query);
    ResultSet getUser(String email);
    ResultSet getUser(String username, String password);
    ResultSet getUser(int userId);
}
