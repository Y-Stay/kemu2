package kemu2daochu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbcUitl {
	private static Properties prop=new Properties();
	static {
		try {
			prop.load(jdbcUitl.class.getResourceAsStream("/datebase.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static Connection getconn()
	{
		try {
		     //Class.forName("oracle.jdbc.driver.OracleDriver");
		     Class.forName(prop.getProperty("driverClass"));
		     Connection con=DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("pass"));
		     //Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:MYORCL","scott","yuan123123");
		      return con;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void clos(Connection con,Statement stat,ResultSet rs) {
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(stat!=null)
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void clos1(Connection con,PreparedStatement pstat,ResultSet rs) {
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(pstat!=null)
			try {
				pstat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
