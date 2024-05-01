package projekt_programko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatabaseConnection {
			

			private static final String URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11703245";
		    private static final String USER = "sql11703245";
		    private static final String PASSWORD = "JBwCA1icNh";

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

			    	public static void nacitatzDB(String nazev, List <String> autor, int rok_vydani, String typ ,Zanr zanr,int rocniKod) {
			        try {
			            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			            Statement stmt = conn.createStatement();
			            ResultSet rs = stmt.executeQuery("SELECT * FROM tabulka");
			            while (rs.next()) {
			                book book = new book(); 
	
			                book.setNazev(rs.getString("nazev"));
	
			                String autori = rs.getString("autor");
			                book.setAutor(Arrays.asList(autori.split(",")));
	
			                book.setRok_vydani(rs.getInt("rok_vydani"));
			                book.set_stav_vypujcky(rs.getBoolean("stav_vypujcky"));
			                book.setTyp(rs.getString("typ"));
	
			                String zanr = rs.getString("zanr");
			                book.setZaner(Zanr.valueOf(zanr));
	
			                int rocniKod = rs.getInt("rocniKod");
			                if (!rs.wasNull()) {
			                    book.setRocniKod(rocniKod);
			                }
	
			                // Optionally, you can do something with the loaded book object, like adding it to a collection
			            }
			            rs.close();
			            stmt.close();
			            conn.close();
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }
			    }

public static void ulozitdoDB(String nazev, List <String> autor, int rok_vydani, String typ ,Zanr zanr,int rocniKod) {
    	try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
