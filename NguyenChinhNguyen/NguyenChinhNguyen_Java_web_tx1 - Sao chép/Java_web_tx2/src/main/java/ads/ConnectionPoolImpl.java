package ads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {

    private String driver;        // Trình điều khiển JDBC
    private String username;      // Tên tài khoản cơ sở dữ liệu
    private String password;      // Mật khẩu
    private String url;           // Đường dẫn kết nối cơ sở dữ liệu

    private Stack<Connection> pool; // Bộ nhớ lưu trữ các kết nối

    // Singleton Design Pattern
    private static ConnectionPool cp;

    // Constructor private để đảm bảo Singleton
    private ConnectionPoolImpl() {
        // Xác định trình điều khiển JDBC
        this.driver = "com.mysql.cj.jdbc.Driver";

        // Xác định đường dẫn cơ sở dữ liệu
        this.url = "jdbc:mysql://localhost:3306/quanlykhachsan_db?allowMultiQueries=true";

        // Xác định tài khoản làm việc
        this.username = "quanly_nguyennc";
        this.password = "12345";

        // Nạp trình điều khiển JDBC
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Khởi tạo bộ nhớ lưu trữ các kết nối
        this.pool = new Stack<>();
    }

    // Lấy kết nối từ ConnectionPool
    @Override
    public Connection getConnection(String objectName) throws SQLException {
        if (this.pool.isEmpty()) {
            // Tạo một kết nối mới nếu pool trống
            System.out.println(objectName + " has created a new Connection.");
            return DriverManager.getConnection(this.url, this.username, this.password);
        } else {
            // Lấy một kết nối đã lưu trữ
            System.out.println(objectName + " has popped the Connection.");
            return this.pool.pop();
        }
    }

    // Thu hồi kết nối và lưu lại vào ConnectionPool
    @Override
    public void releaseConnection(Connection con, String objectName) throws SQLException {
        System.out.println(objectName + " has pushed a Connection.");
        this.pool.push(con);
    }

    // Phương thức Singleton để lấy instance của ConnectionPoolImpl
    public static ConnectionPool getInstance() {
        if (cp == null) {
            synchronized (ConnectionPool.class) {
                if (cp == null) {
                    cp = new ConnectionPoolImpl();
                }
            }
        }
        return cp;
    }
}
