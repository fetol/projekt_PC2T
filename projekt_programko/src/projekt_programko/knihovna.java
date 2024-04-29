package projekt_programko;


import java.util.*;

public class Knihovna {
	private Map<String, book> knihovna;
	
	public Knihovna() {
		this.knihy = new HashMap<>();
	}
	
	public void addKnihu(String nazev, List<String> autor, int rok_vydani) {
		knihovna.put(nazev, new book(nazev,autor,rok_vydani));
	}
	
	public void updateKnihy (String nazev, String autor, int rok_vydani, boolean stav_vypujcky) {
		book book = knihy.get(nazev);
		if (book != null) {
			book.getAutor().add(autor)
			book.set_stav_vypujcky(vypujceno);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
