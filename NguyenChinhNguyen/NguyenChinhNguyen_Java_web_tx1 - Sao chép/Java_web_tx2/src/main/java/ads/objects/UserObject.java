package ads.objects;


import java.time.LocalDate;

import lombok.Data;

@Data
public class UserObject {
    private int KhachHangID;       // ID khách hàng
    private String HoTen;          // Họ và tên
    private String Email;          // Địa chỉ email
    private String MatKhau;        // Mật khẩu
    private String SoDienThoai;   // Số điện thoại
    private String DiaChi;         // Địa chỉ
    private LocalDate NgayDangKy;     // Ngày đăng ký
	
	
}
