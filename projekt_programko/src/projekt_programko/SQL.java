package projekt_programko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
     
     public static HashMap<String, book> load(String tabulka)  {
    	 HashMap<String, book> knihovna = new HashMap<>();
         String sql = "SELECT * FROM " + tabulka;
         try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 String nazev = rs.getString("nazev");
                 List<String> autor = Arrays.asList(rs.getString("autor").split(", "));
                 int rok_vydani = rs.getInt("rok_vydani");
                 String typ = rs.getString("typ");
                 Zanr zanr = Zanr.valueOf(rs.getString("zanr"));
                 int rocniKod = rs.getInt("rocniKod");
                 boolean stav_vypujcky = rs.getBoolean("stav_vypujcky");
                 book book = new book(nazev, autor, rok_vydani, typ, zanr, rocniKod, stav_vypujcky);
                 knihovna.put(nazev, book);
             }
             rs.close();
             pstmt.close();
         } catch (SQLException e) {
             System.err.println("Failed to load data from the database: " + e.getMessage());
         }
         
         return knihovna;
     }
     
    public static void Upload(book book) throws SQLException {
    	String sql = "INSERT INTO " + "tabulka" + " (nazev, autor, rok_vydani, typ, zanr, rocniKod, stav_vypujcky) VALUES (?, ?, ?, ?, ?, ?, ?)";
    	 try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
             PreparedStatement pstmt = con.prepareStatement(sql);
             pstmt.setString(1, book.getNazev());
             pstmt.setString(2, String.join("` ", book.getAutor()));
             pstmt.setInt(3, book.getRok_vydani());
             pstmt.setString(4, book.getTyp());
             pstmt.setString(5, book.getZanr().toString());
             pstmt.setInt(6, book.getRocniKod());
             pstmt.setBoolean(7, book.getStav_vypujcky());
             pstmt.executeUpdate();
         } catch (SQLException e) {
        	 System.err.println("Failed to update book: " + e.getMessage());
         }
    }
    public static void Update(book book) throws SQLException {
    	String sql ="UPDATE " + "tabulka" +  " SET nazev = ?, autor = ?, rok_vydani = ?, stav_vypujcky = ?" +
        " WHERE nazev = ?";
    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, book.getNazev());
            pstmt.setString(2, String.join(", ", book.getAutor()));
            pstmt.setInt(3, book.getRok_vydani());
            pstmt.setBoolean(4, book.getStav_vypujcky());
            pstmt.setString(5, book.getNazev());
            pstmt.executeUpdate();
    	} catch (SQLException e) {
            System.err.println("Failed to update book: " + e.getMessage());
        }
    }
    public static void Delete(book book) throws SQLException {
    	String sql = "DELETE FROM tabulka WHERE nazev = ?";
    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, book.getNazev());
            pstmt.executeUpdate();
    	 } catch (SQLException e) {
             System.err.println("Failed to delete book: " + e.getMessage());
         }
    }
    
    public static void disconnect() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (con != null) {
                if (!con.isClosed()) {
                    con.close();
                    System.out.println("Disconnected from the database.");
                } else {
                    System.out.println("Already disconnected from the database.");
                }
            } else {
                System.out.println("Not connected to the database.");
            }
        } catch (SQLNonTransientConnectionException e) {
            System.err.println("Failed to disconnect from the database: Too many connections");
        } catch (SQLException e) {
            System.err.println("Failed to disconnect from the database: " + e.getMessage());
        }
    }
    }
	
