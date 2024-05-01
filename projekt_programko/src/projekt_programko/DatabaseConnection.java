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
		            conn = DriverManager.getConnection(URL, USER, PASSWORD);
		            System.out.println("Připojení k databázi bylo úspěšné.");
		        } catch (SQLException e) {
		            System.out.println("Připojení k databázi se nezdařilo.");
		            e.printStackTrace();
		        }
		        return conn;
		    }	   

public static void nacitatzDB() {
    try {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM sql11703245");
        while(rs.next()){
        	book book = new book(null, null, 0, null, null, 0);
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
        String sql = "INSERT INTO  sql11703245 (nazev, autor, rok_vydani, stav_vypujcky, typ, zanr, rocniKod) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        book book = new book(sql, null, 0, sql, null, 0);
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
