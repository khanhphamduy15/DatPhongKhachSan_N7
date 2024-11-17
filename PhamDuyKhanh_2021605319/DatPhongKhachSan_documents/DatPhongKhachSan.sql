create database quanlykhachsan;
use quanlykhachsan;
drop database quanlykhachsan;

CREATE TABLE khachhang(
    KhachHangID INT PRIMARY KEY AUTO_INCREMENT,
    HoTen nvarchar(50) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    MatKhau VARCHAR(255) NOT NULL,
    SoDienThoai VARCHAR(15),
    DiaChi nvarchar(255),
    NgayDangKy DATE DEFAULT(current_date())
);
CREATE TABLE loaiPhong (
    LoaiPhongID INT PRIMARY KEY AUTO_INCREMENT,
    TenLoai nvarchar(50) NOT NULL,
    MoTa TEXT
);
CREATE TABLE phong (
    PhongID char(10) PRIMARY KEY,
    TenPhong nvarchar(100) NOT NULL,
    LoaiPhongID INT NOT NULL,
	AnhPhong varchar(1000),
    GiaPhong DECIMAL(10, 2) NOT NULL,
    SoNguoiToiDa INT,
    TinhTrang ENUM('ConTrong', 'DaDat') DEFAULT 'ConTrong',
	FOREIGN KEY (LoaiPhongID) REFERENCES loaiPhong(LoaiPhongID) ON DELETE CASCADE
);




CREATE TABLE donDat (
    DonDatPhongID INT PRIMARY KEY AUTO_INCREMENT,
    KhachHangID INT,
    PhongID char(10),
    NgayDatPhong DATE DEFAULT(current_date()),
    NgayNhanPhong DATE NOT NULL,
    NgayTraPhong DATE NOT NULL,
    SoLuong INT,
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