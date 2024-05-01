package projekt_programko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;



public class DatabaseConnection {
			

			public static final String URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11703245";
		    public static final String USER = "sql11703245";
		    public static final String PASSWORD = "JBwCA1icNh";

		    public static Connection getConnection() {
		        Connection conn = null;
		        try {
		        	try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
		            conn = DriverManager.getConnection(URL, USER, PASSWORD);
		            System.out.println("Připojení k databázi bylo úspěšné.");
		        } catch (SQLException e) {
		            System.out.println("Připojení k databázi se nezdařilo.");
		            e.printStackTrace();
		        }
		        return conn;
		    }	   
		    public static void ulozitdoDB(String nazev, List<String> autor, int rok_vydani, String typ, Zanr zanr, int rocniKod) {
		        try {
		            Connection conn = getConnection(); // Get connection using your existing method
		            String sql = "INSERT INTO tabulka (nazev, autor, rok_vydani, stav_vypujcky, typ, zanr, rocniKod) VALUES (?, ?, ?, ?, ?, ?, ?)";
		            PreparedStatement pstmt = conn.prepareStatement(sql);
		            pstmt.setString(1, nazev);
		            pstmt.setString(2, String.join(",", autor)); 
		            pstmt.setInt(3, rok_vydani);
		            pstmt.setBoolean(4, false); 
		            pstmt.setString(5, typ);
		            pstmt.setString(6, zanr.name()); 
		            pstmt.setInt(7, rocniKod);
		            pstmt.executeUpdate();
		            pstmt.close();
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
}
		
