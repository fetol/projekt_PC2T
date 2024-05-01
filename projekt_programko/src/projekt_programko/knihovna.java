package projekt_programko;
import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class knihovna {
	private Map<String, book> knihovna;
	
	public knihovna() {
		this.knihovna = new HashMap<>();
	}
	
	 public void addKnihu(Scanner scanner) {
	        System.out.println("Aky typ knihy:");
	        System.out.println("1.Ucebnica");
	        System.out.println("2.Romany");
	        int bookVyber = pouzeCelaCisla(scanner);
	        
	        scanner.nextLine();

	        System.out.println("Zadaj nazov: ");
	        String nazev = scanner.nextLine();

	        System.out.println("Zadaj autora ak viac oddel ich ciarkov: ");
	        String[] authorsArray = scanner.nextLine().split(",");
	        List<String> autor = new ArrayList<>();
	        for (String author : authorsArray) {
	            autor.add(author.trim());
	        }

	        System.out.println("Zadaj rok vydania: ");
	        int rok_vydani = pouzeCelaCisla(scanner);

	        if (bookVyber == 1) {
	            System.out.println("Zadaj pre aku triedu to je: ");
	            int rocniKod = pouzeCelaCisla(scanner);
	            knihovna.put(nazev, new book(nazev, autor, rok_vydani, "ucebnice", null, rocniKod));
	            System.out.println("Ucebnica pridana do kniznice: " + nazev);
	            System.out.println("Typ: Ucebnica");
	            System.out.println("Trieda cislo: " + rocniKod);
	        } else if (bookVyber == 2) {
	            System.out.println("Vyber si zaner:");
	            System.out.println("1. Historický");
	            System.out.println("2. Fantazie");
	            System.out.println("3. ROMANTIKA");
	            System.out.println("4. VOJNA");
	            System.out.println("5. THRILLER");
	            int zanerChoice = pouzeCelaCisla(scanner);
	            Zanr zanr = Zanr.values()[zanerChoice - 1];
	            knihovna.put(nazev, new book(nazev, autor, rok_vydani, "roman", zanr, 0));
	            System.out.println("Roman pridany do kniznice: " + nazev);
	            System.out.println("Typ: Roman");
	            System.out.println("Zaner: " + zanr);
	        } else {
	            System.out.println("Zly input.Kniha neni pridana do kniznice.");
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
		
	public boolean smazaniknihy(String nazev) {
		if (knihovna.remove(nazev)!=null)
			return true;
		return false;
		}
		
		
	public void listovaniKnih() {
	        for (book book : knihovna.values()) {
	            System.out.println("Nazev: " + book.getNazev());
	            System.out.println("Autor: " + book.getAutor());
	            System.out.println("Rok vydani: " + book.getRok_vydani());
	            System.out.println("Typ knihy: "+ book.getTyp());
	            System.out.println("Zaner: "+ book.getZanr());
	            System.out.println("Pre triedu: "+ book.getRocniKod());
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
	            System.out.println("Zaner: "+ book.getZanr());
	            System.out.println("Pre triedu: "+ book.getRocniKod());
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
	                System.out.println("Zaner: "+ book.getZanr());
		            System.out.println("Pre triedu: "+ book.getRocniKod());
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
				e.printStackTrace();
			}
		} else {
			System.out.println("Kniha nebyla nalezena");
		}
	}
	
	public void podlezanru(Zanr zanr) {
		boolean found = false;
		for (book book : knihovna.values()) {
			if (book.getZanr().equals(zanr)) {
	               System.out.println("Nazev: " + book.getNazev());
	              System.out.println("Rok vydani: " + book.getRok_vydani());
	              System.out.println("Typ: " + book.getTyp());
	              System.out.println("Zaner: "+ book.getZanr());
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
					System.out.println("Rok vydaní: " + book.getRok_vydani());
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
	public boolean nacistknihudodzosouboru(String nazev_souboru) {
		try {
	        File file = new File(nazev_souboru);
	        Scanner scanner = new Scanner(file);
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] parts = line.split(",");
	            if (parts.length == 5) {
	                String nazevKnihy = parts[0];
	                String autor = parts[1];
	                int rokVydani = Integer.parseInt(parts[2]);
	                String typ = parts[3];
	                boolean pujceno = Boolean.parseBoolean(parts[4]);
	            }
	        }
	        scanner.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		return true;
	}
	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
		private static final String DB_URL = "mysql.fetol-dev.svc.cluster.local";
	    private static final String DB_USER = "fetol";
	    private static final String DB_PASSWORD = "oldajefrajer";


	    public void connect() {
	            System.out.println("Connected to the database.");
	       
	        }
	
}
	

		
	
	
	 
		 
	
	//DODELAT!!!! Nacitani knihy ze souboru
	// DODELAT!!! SQL NACITANI A UKLADANI 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

