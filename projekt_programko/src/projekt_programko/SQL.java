package projekt_programko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class SQL {

		private static final String URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11703245";
		private static final String USER = "sql11703245";
		private static final String PASSWORD = "JBwCA1icNh";

     public static Connection connect() {
        	        Connection con = null;
        	        try {
        	        	Class.forName("com.mysql.cj.jdbc.Driver");
        	            con = DriverManager.getConnection(URL, USER, PASSWORD);
        	            if (con != null) {
        	                System.out.println("pripojeny k databazi uspesne!");
        	            } else {
        	                System.out.println("nepripojeny");
        	            }
        	        } catch (ClassNotFoundException | SQLException e) {
        	            e.printStackTrace();
        	        }
        	        return con;
            }
     public static void nacitatzDB() {
    	 try {
    		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM tabulka");
    		while(rs.next()){
    		book book = new book(); //chyba tu nevim co
         	book.setNazev(rs.getString("nazev"));
         	String autori = rs.getString("autor");
         	book.setAutor(Arrays.asList(autori.split(",")));
            book.setRok_vydani(rs.getInt("rok_vydani"));
            book.set_stav_vypujcky = rs.getBoolean("stav_vypujcky");
            book.setTyp = rs.getString("typ");
            String zanr = rs.getString("zanr");
            book.setZaner(Zanr.valueOf(zanr));
            book.setRocniKod(rs.getInt("rocniKod"));
         }
         rs.close();
         stmt.close();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
     public static void ulozitdoDB() {
    	    try {
    	        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
    	        String sql = "INSERT INTO  tabulka (nazev, autor, rok_vydani, stav_vypujcky, typ, zanr, rocniKod) VALUES (?, ?, ?)";
    	        PreparedStatement pstmt = conn.prepareStatement(sql);
    	       knihovna. = new book(); //chyba tu nevim co ahhhhoj
    	        pstmt.setString(1, book.getNazev());
    	        pstmt.setString(2, String.join(",", book.getAutor())); 
    	        pstmt.setInt(3, book.getRok_vydani());
    	        pstmt.setBoolean(4, book.stav_vypujcky());
    	        pstmt.setString(5, book.getTyp());
    	        pstmt.setString(6, book.getZanr().name());
    	        pstmt.setInt(7, book.getRocniKod());
    	        pstmt.executeUpdate();
    	        pstmt.close();
    	        conn.close();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
     }
}
	
