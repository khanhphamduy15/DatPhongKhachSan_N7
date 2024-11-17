create database quanlykhachsan_db;
use quanlykhachsan_db;

CREATE TABLE khachhang(
    KhachHangID INT PRIMARY KEY AUTO_INCREMENT,
    HoTen nvarchar(50) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    MatKhau VARCHAR(255) NOT NULL,
    SoDienThoai VARCHAR(15),
    DiaChi nvarchar(255),
    NgayDangKy DATE DEFAULT(current_date())
);

CREATE TABLE phong (
    PhongID char(10) PRIMARY KEY,
    TenPhong nvarchar(100) NOT NULL,
    LoaiPhong nvarchar(50) NOT NULL,
    GiaPhong DECIMAL(10, 2) NOT NULL,
    MoTa TEXT,
	Anh BLOB,
    SoNguoiToiDa INT,
    TinhTrang ENUM('ConTrong', 'DaDat') DEFAULT 'ConTrong'
);

CREATE TABLE donDat (
    DonDatPhongID INT PRIMARY KEY AUTO_INCREMENT,
    KhachHangID INT,
    PhongID char(10),
    NgayDatPhong DATE DEFAULT(current_date()),
    NgayNhanPhong DATE NOT NULL,
    NgayTraPhong DATE NOT NULL,
    TrangThai ENUM('DatThanhCong', 'DaHuy') DEFAULT 'DatThanhCong',
    FOREIGN KEY (KhachHangID) REFERENCES KhachHang(KhachHangID) ON DELETE CASCADE,
    FOREIGN KEY (PhongID) REFERENCES Phong(PhongID) ON DELETE CASCADE
);

CREATE TABLE quanTri (
    AdminID INT PRIMARY KEY AUTO_INCREMENT,
    HoTen nvarchar(50) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    MatKhau VARCHAR(255) NOT NULL
);


INSERT INTO quanTri (HoTen, Email, MatKhau)
VALUES 
    ('Nguyen Chinh Nguyen', 'thanlokinguyen@gmail.com', 'Ad567890.'),
    ('Phan Quang Minh', 'thanlokinguyenlq@gmail.com', '187980576');
    
    
    
    INSERT INTO khachhang (HoTen, Email, MatKhau, SoDienThoai, DiaChi, NgayDangKy)
VALUES
    ('Nguyen Minh Tu', 'nguyenminhtu1@gmail.com', '12345', '0901234567', 'Hà Nội', CURRENT_DATE()),
    ('Pham Thi Mai', 'phamthimai2@gmail.com', '12345', '0902345678', 'TP.HCM', CURRENT_DATE()),
    ('Tran Minh Hieu', 'tranminhieu3@gmail.com', '12345', '0903456789', 'Đà Nẵng', CURRENT_DATE()),
    ('Le Thi Lan', 'lethilan4@gmail.com', '12345', '0904567890', 'Cần Thơ', CURRENT_DATE()),
    ('Nguyen Thi Lan', 'nguyenthilan5@gmail.com', '12345', '0905678901', 'Huế', CURRENT_DATE()),
    ('Hoang Minh Long', 'hoangminhlong6@gmail.com', '12345', '0906789012', 'Bắc Ninh', CURRENT_DATE()),
    ('Nguyen Thi Kim', 'nguyenthikim7@gmail.com', '12345', '0907890123', 'Nha Trang', CURRENT_DATE()),
    ('Nguyen Thi Lan', 'nguyenthilan8@gmail.com', '12345', '0908901234', 'Hải Phòng', CURRENT_DATE()),
    ('Pham Minh Tu', 'phaminhtu9@gmail.com', '12345', '0909012345', 'Vũng Tàu', CURRENT_DATE()),
    ('Le Minh Hoa', 'leminhhoa10@gmail.com', '12345', '0900123456', 'Quảng Ninh', CURRENT_DATE()),
    ('Tran Thi Lan', 'tranthilan11@gmail.com', '12345', '0901234567', 'Bến Tre', CURRENT_DATE()),
    ('Nguyen Thi Hoa', 'nguyenthihua12@gmail.com', '12345', '0902345678', 'Quảng Nam', CURRENT_DATE()),
    ('Pham Minh Hoa', 'phaminhhoa13@gmail.com', '12345', '0903456789', 'Lâm Đồng', CURRENT_DATE()),
    ('Tran Thi Lan', 'tranthilan14@gmail.com', '12345', '0904567890', 'Tây Ninh', CURRENT_DATE()),
    ('Nguyen Minh Hoa', 'nguyenminhhoa15@gmail.com', '12345', '0905678901', 'Vĩnh Long', CURRENT_DATE()),
    ('Nguyen Thi Mai', 'nguyenthimai16@gmail.com', '12345', '0906789012', 'Bắc Giang', CURRENT_DATE()),
    ('Tran Minh Tuan', 'tranminhtuan17@gmail.com', '12345', '0907890123', 'Hòa Bình', CURRENT_DATE()),
    ('Le Minh Sơn', 'leminhson18@gmail.com', '12345', '0908901234', 'Thái Nguyên', CURRENT_DATE()),
    ('Nguyen Thi Lan', 'nguyenthilan19@gmail.com', '12345', '0909012345', 'Vĩnh Phúc', CURRENT_DATE()),
    ('Pham Minh Tu', 'phaminhtu20@gmail.com', '12345', '0900123456', 'Hải Dương', CURRENT_DATE());


INSERT INTO phong (PhongID, TenPhong, LoaiPhong, GiaPhong, MoTa, Anh, SoNguoiToiDa, TinhTrang)
VALUES
    -- Tầng 1
    ('T1P01', 'King Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    ('T1P02', 'Date Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    ('T1P03', 'Basic Room', 'Phòng Đôi', 800000, 'Phòng 1 giường đôi, view hồ bơi', NULL, 2, 'ConTrong'),
    ('T1P04', 'VIP Room', 'Phòng Suite', 1500000, 'Phòng suite cao cấp, view biển', NULL, 3, 'ConTrong'),
    ('T1P05', 'Queen Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view thành phố', NULL, 1, 'ConTrong'),
    ('T1P06', 'King Room', 'Phòng Đôi', 800000, 'Phòng 1 giường đôi, view hồ bơi', NULL, 2, 'ConTrong'),
    ('T1P07', 'Date Room', 'Phòng Suite', 1500000, 'Phòng suite cao cấp, view biển', NULL, 3, 'ConTrong'),
    ('T1P08', 'Basic Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    -- Tầng 2
    ('T2P01', 'VIP Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    ('T2P02', 'Queen Room', 'Phòng Đôi', 800000, 'Phòng 1 giường đôi, view hồ bơi', NULL, 2, 'ConTrong'),
    ('T2P03', 'King Room', 'Phòng Suite', 1500000, 'Phòng suite cao cấp, view biển', NULL, 3, 'ConTrong'),
    ('T2P04', 'Date Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    ('T2P05', 'Basic Room', 'Phòng Đôi', 800000, 'Phòng 1 giường đôi, view hồ bơi', NULL, 2, 'ConTrong'),
    ('T2P06', 'VIP Room', 'Phòng Suite', 1500000, 'Phòng suite cao cấp, view biển', NULL, 3, 'ConTrong'),
    ('T2P07', 'Queen Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    ('T2P08', 'King Room', 'Phòng Đôi', 800000, 'Phòng 1 giường đôi, view hồ bơi', NULL, 2, 'ConTrong'),
    -- Tầng 3
    ('T3P01', 'Date Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    ('T3P02', 'Basic Room', 'Phòng Đôi', 800000, 'Phòng 1 giường đôi, view hồ bơi', NULL, 2, 'ConTrong'),
    ('T3P03', 'VIP Room', 'Phòng Suite', 1500000, 'Phòng suite cao cấp, view biển', NULL, 3, 'ConTrong'),
    ('T3P04', 'Queen Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    ('T3P05', 'King Room', 'Phòng Đôi', 800000, 'Phòng 1 giường đôi, view hồ bơi', NULL, 2, 'ConTrong'),
    ('T3P06', 'Date Room', 'Phòng Suite', 1500000, 'Phòng suite cao cấp, view biển', NULL, 3, 'ConTrong'),
    ('T3P07', 'Basic Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    ('T3P08', 'VIP Room', 'Phòng Đôi', 800000, 'Phòng 1 giường đôi, view hồ bơi', NULL, 2, 'ConTrong'),
    -- Tầng 4
    ('T4P01', 'Queen Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    ('T4P02', 'King Room', 'Phòng Đôi', 800000, 'Phòng 1 giường đôi, view hồ bơi', NULL, 2, 'ConTrong'),
    ('T4P03', 'Date Room', 'Phòng Suite', 1500000, 'Phòng suite cao cấp, view biển', NULL, 3, 'ConTrong'),
    ('T4P04', 'Basic Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    ('T4P05', 'VIP Room', 'Phòng Đôi', 800000, 'Phòng 1 giường đôi, view hồ bơi', NULL, 2, 'ConTrong'),
    ('T4P06', 'Queen Room', 'Phòng Suite', 1500000, 'Phòng suite cao cấp, view biển', NULL, 3, 'ConTrong'),
    ('T4P07', 'King Room', 'Phòng Đơn', 500000, 'Phòng 1 giường đơn, view đẹp', NULL, 1, 'ConTrong'),
    ('T4P08', 'Date Room', 'Phòng Đôi', 800000, 'Phòng 1 giường đôi, view hồ bơi', NULL, 2, 'ConTrong');

    
INSERT INTO donDat (KhachHangID, PhongID, NgayDatPhong, NgayNhanPhong, NgayTraPhong, TrangThai)
VALUES
    (1, 'T1P01', CURRENT_DATE(), '2024-11-20', '2024-11-22', 'DatThanhCong'),
    (2, 'T1P02', CURRENT_DATE(), '2024-11-21', '2024-11-23', 'DatThanhCong'),
    (3, 'T1P03', CURRENT_DATE(), '2024-11-22', '2024-11-24', 'DatThanhCong'),
    (4, 'T1P04', CURRENT_DATE(), '2024-11-23', '2024-11-25', 'DatThanhCong'),
    (5, 'T2P01', CURRENT_DATE(), '2024-11-24', '2024-11-26', 'DatThanhCong'),
    (6, 'T2P02', CURRENT_DATE(), '2024-11-25', '2024-11-27', 'DatThanhCong'),
    (7, 'T2P03', CURRENT_DATE(), '2024-11-26', '2024-11-28', 'DatThanhCong'),
    (8, 'T3P01', CURRENT_DATE(), '2024-11-27', '2024-11-29', 'DatThanhCong'),
    (9, 'T3P02', CURRENT_DATE(), '2024-11-28', '2024-11-30', 'DatThanhCong'),
    (10, 'T3P03', CURRENT_DATE(), '2024-11-29', '2024-12-01', 'DatThanhCong');



