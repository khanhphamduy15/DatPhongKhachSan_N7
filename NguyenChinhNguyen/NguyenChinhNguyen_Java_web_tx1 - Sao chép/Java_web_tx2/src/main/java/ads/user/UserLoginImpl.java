package ads.user;

import ads.objects.UserObject;
import java.sql.ResultSet;

public class UserLoginImpl implements UserLogin {

    private User userService;

    public UserLoginImpl() {
        userService = new UserImpl();  // Sử dụng UserImpl để xử lý tìm người dùng
    }

    @Override
    public boolean login(String username, String password) {
        ResultSet rs = userService.getUser(username, password);
        try {
            if (rs.next()) {
                System.out.println("Login successful");
                return true;
            } else {
                System.out.println("Invalid username or password");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
