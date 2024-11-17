package ads.objects;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RoomObject {
    private String PhongID;        
    private String TenPhong;        
    private String LoaiPhong;       
    private double GiaPhong;        
    private String MoTa;            
    private byte[] Anh;             
    private int SoNguoiToiDa;       
    private String TinhTrang;       
}