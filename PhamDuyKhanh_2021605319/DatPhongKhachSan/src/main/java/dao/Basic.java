package dao;

import java.util.ArrayList;
import java.sql.*;

public interface Basic {
	boolean add(PreparedStatement pre);
	boolean edit(PreparedStatement pre);
	boolean del(PreparedStatement pre);

	ArrayList<ResultSet> gets(String multiSelect);
	ResultSet get(String sql,int value);
	ResultSet get(String sql,String name, String pass);
	
	void releaseConnection();

	
}
