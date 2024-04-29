package projekt_programko;
import java.io.*;
import java.util.*;

public class knihovna {
	private Map<String, book> knihovna;
	
	public knihovna() {
		this.knihovna = new HashMap<>();
	}
	
	public void addKnihu(String nazev, List<String> autor, int rok_vydani, String typ) {
		if (typ.equals("ucebnice")) {
			typ="ucebnice";
			knihovna.put(nazev, new book (nazev, autor, rok_vydani, "ucebnice"));
		} else {
			typ="novela";
			knihovna.put(nazev, new book(nazev, autor, rok_vydani, "novela"));
		}
		
	
	}
	
	public void updateKnihy (String nazev, String autor, int rok_vydani, boolean stav_vypujcky) {
		book book = knihovna.get(nazev);
		if (book != null) {
			book.getAutor().add(autor);
			book.setRok_vydani(rok_vydani);
			book.set_stav_vypujcky(stav_vypujcky);
			
			
		
		}
	}
		
	public void smazaniknihy(String nazev) {
			knihovna.remove(nazev);
		}
		
		
	public void listovaniKnih() {
	        for (book book : knihovna.values()) {
	            System.out.println("Nazev: " + book.getNazev());
	            System.out.println("Autor: " + book.getAutor());
	            System.out.println("Rok vydani: " + book.getRok_vydani());
	            System.out.println("Typ knihy: "+ book.getTyp());
	            System.out.println("Pujceno: " + (book.stav_vypujcky() ? "Ano" : "Ne"));
	            System.out.println();
	        }
		
	
		  
	  }
	
	
	public void hledani_knihy(String nazev) {
		book book = knihovna.get(nazev);
		if (book != null) {
				System.out.println("Nazev: " + book.getNazev());
	            System.out.println("Autor: " + book.getAutor());
	            System.out.println("Rok vydani: " + book.getRok_vydani());
	            System.out.println("Typ knihy: "+ book.getTyp());
	            System.out.println("Pujceno: " + (book.stav_vypujcky() ? "Ano" : "Ne"));
	            System.out.println();
		} else {
			System.out.println("Kniha nebyla nalezena");
		}
	}
	
	public void podleAutora(String autor) {
		boolean found = false;
		for(book book : knihovna.values()) {
			if (book.getAutor().contains(autor)) {
				System.out.println("Nazev: " + book.getNazev());
				System.out.println("Rok vydani: " + book.getRok_vydani());
				 System.out.println("Typ knihy: "+ book.getTyp());
				System.out.println();
				found = true;
			}
		}
		if(!found) {
			System.out.println("Žádna kniha nebyla nalezena pro autora:  "+ autor);
		}
	}
	
	public void listovaniPujcenychKnih() {
		boolean found = false;
		for (book book : knihovna.values()) {
			if(book.stav_vypujcky()) {
				 	System.out.println("Nazev: " + book.getNazev());
	                System.out.println("Rok vydani: " + book.getRok_vydani());
	                System.out.println("Typ knihy: "+ book.getTyp());
	                
	                System.out.println();
	                found = true;	
			}
		}
		if(!found) {
			System.out.println("Žádné vypujčené knihy");
		}
	}
	
	public void ulozitknihudodsouboru(String nazev) {
		book book = knihovna.get(nazev);
		if (book != null) {
			try(PrintWriter writer = new PrintWriter(new FileWriter(nazev + ".txt"))){
				writer.println("Nazev,Autor,Rok vydani Typ ,pujceno");
                writer.println(book.getNazev() + "," + String.join(", ", book.getAutor()) + "," + book.getRok_vydani() + ","+ (book.getTyp()) + "," +(book.stav_vypujcky() ? "Yes" : "No"));
                System.out.println("Kniha byla ulozena do souboru: " + nazev + ".txt");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Kniha nebyla nalezena");
		}
	}
	
	public void podlezanru(String zanr) {
		boolean found = false;
		for (book book : knihovna.values()) {
			if (book.getZanr().equals(zanr)) {
	               System.out.println("Nazev: " + book.getNazev());
	              System.out.println("Rok vydani: " + book.getRok_vydani());
	              System.out.println("Typ: " + (book.getTyp().equals("ucebnice") ? "Ucebnice" : "novela"));
	              System.out.println();
	               found = true;
	             }
		}
		if(!found) {
			System.out.println("Kniha nebyla nalezena");
		}
	}
	 
	public void vypujceni(String nazev) {
		book book = knihovna.get(nazev);
		 if (book !=null) {
			 if(!book.stav_vypujcky()) {
				 book.set_stav_vypujcky(true);
				 System.out.println("Kniha " + nazev + " byla vypujcena");
			 } else {
				 System.out.println("Kniha " + nazev + " jiz byla vypujcena!");
			 } 
			 } else {
				 System.out.println("Kniha " + nazev + " Nebyla nalezena");
				 
			 }
	 	}
	public void vraceni(String nazev) {
		book book = knihovna.get(nazev);
		 if (book !=null) {
			 if(!book.stav_vypujcky()) {
				 book.set_stav_vypujcky(false);
				 System.out.println("Kniha " + nazev + " byla vracena");
			 } else {
				 System.out.println("Kniha " + nazev + " jiz byla vracena!");
			 } 
			 } else {
				 System.out.println("Kniha " + nazev + " Nebyla nalezena");
				 
			 }
	}
	public void razeniKnihyPodleAutoraChronologicky(String autor) {
		List<book> knizkypodleautora = new ArrayList<>();
		
		
		for(book book : knihovna.values()) {
			if (book.getAutor().contains(autor)) {
				knizkypodleautora.add(book);
			}
		}
		Collections.sort(knizkypodleautora, (kniha1, kniha2) -> Integer.compare(kniha1.getRok_vydani(), kniha2.getRok_vydani()));
			if(!knizkypodleautora.isEmpty()) {
				System.out.println("Knihy podle autora " + autor + " (chronologicky)");
				for (book book : knizkypodleautora) {
					System.out.println("Nazev: "+ book.getNazev());
					System.out.println("Rok vydaní" + book.getRok_vydani());
					System.out.println();
				}
			} else {
				System.out.println("Zadne knizky nebyli nalezeny pro autora: "+ autor);
			}
	
}
	public void razeniAbecedne() {
		List<book> abecedicka = new ArrayList<>(knihovna.values());
		
		Collections.sort(abecedicka, (kniha1, kniha2) -> kniha1.getNazev().compareTo(kniha2.getNazev()));
		
		if (!abecedicka.isEmpty()) {
			System.out.println("Knihy v abecednim poradi");
			for(book book: abecedicka) {
				System.out.println("Nazev: " + book.getNazev());
	            System.out.println("Autor: " + book.getAutor());
	            System.out.println("Rok vydani: " + book.getRok_vydani());
	            System.out.println("Typ knihy: "+ book.getTyp());
	            System.out.println("Pujceno: " + (book.stav_vypujcky() ? "Ano" : "Ne"));
	            System.out.println();
			}
		} else {
			System.out.println("Zadne knihy v knihovne");
		}
		
		
	}
}
	 
		 
	
	//DODELAT!!!! Nacitani knihy ze souboru
	// DODELAT!!! SQL NACITANI A UKLADANI 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

