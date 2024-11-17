package ads.user;

import ads.objects.UserObject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistrationImpl implements UserRegistration {

    private User userService;

    public UserRegistrationImpl() {
        userService = new UserImpl();  // Sử dụng UserImpl để xử lý thêm dữ liệu
    }

    @Override
    public boolean registerUser(UserObject user) {
        // Kiểm tra xem email đã tồn tại chưa
        ResultSet result = userService.getUser(user.getEmail());
        try {
            if (result != null && result.next()) {
                System.out.println("Email already registered.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        // Thêm người dùng mới
        return userService.addUser(user); // Gọi phương thức addUser của UserImpl
    }
}
