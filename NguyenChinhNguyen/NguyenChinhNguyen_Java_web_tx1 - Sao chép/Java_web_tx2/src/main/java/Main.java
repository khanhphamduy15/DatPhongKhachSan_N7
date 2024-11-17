import ads.objects.UserObject;
import ads.user.UserRegistrationImpl;
import ads.user.UserLoginImpl;
import ads.objects.RoomObject;
import ads.room.RoomImpl;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // Kiểm tra đăng ký người dùng
        UserObject newUser = new UserObject();
        newUser.setHoTen("John Doe"); // Đặt tên người dùng
        newUser.setMatKhau("password123"); // Đặt mật khẩu
        newUser.setEmail("john@example.com"); // Đặt email
        newUser.setNgayDangKy(LocalDate.now()); // Ngày đăng ký là ngày hiện tại

        UserRegistrationImpl registration = new UserRegistrationImpl();
        if (registration.registerUser(newUser)) {
            System.out.println("User registered successfully");
        } else {
            System.out.println("Registration failed");
        }

        // Kiểm tra đăng nhập người dùng
        UserLoginImpl login = new UserLoginImpl();
        login.login(newUser.getEmail(), newUser.getMatKhau()); // Đăng nhập bằng email và mật khẩu
        // Kiểm tra phòng
        RoomObject room = new RoomObject();
        room.setPhongID("R001");
        room.setTenPhong("Deluxe Room");
        room.setLoaiPhong("VIP");
        room.setGiaPhong(new BigDecimal("150.00").doubleValue()); // Chuyển BigDecimal thành double
        room.setMoTa("A luxurious room with sea view");
        room.setSoNguoiToiDa(3); // Số người tối đa
        room.setTinhTrang("ConTrong"); // Tình trạng phòng

        RoomImpl roomService = new RoomImpl();
        if (roomService.addRoom(room)) {
            System.out.println("Room added successfully");
        } else {
            System.out.println("Failed to add room");
        }
    }
}
