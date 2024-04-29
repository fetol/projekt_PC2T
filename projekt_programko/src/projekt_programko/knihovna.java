package projekt_programko;


import java.awt.print.Book;
import java.util.*;

public class knihovna {
	private Map<String, Book> knihovna;
	
	public knihovna() {
		this.knihy = new HashMap<>();
	}
	
	public void addKnihu(String nazev, List<String> autor, int rok_vydani) {
		knihovna.put(nazev, new book(nazev,autor,rok_vydani));
	}
	
	public void updateKnihy (String nazev, String autor, int rok_vydani, boolean stav_vypujcky) {
		Book book = knihy.get(nazev);
		if (book != null) {
			book.getAutor().add(autor)
			book.set_stav_vypujcky(vypujceno);
			book.
		
		}
		public void smazaniknihy(String nazev) {
			knihovna.remove(nazev);
		}
		public void listBooks() {
	        for (Book book : library.values()) {
	            System.out.println("Title: " + book.getClass());
	            System.out.println("Authors: " + book.getAuthors());
	            System.out.println("Year: " + book.getYear());
	            System.out.println("Borrowed: " + (book.isBorrowed() ? "Yes" : "No"));
	            System.out.println();
	        }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
