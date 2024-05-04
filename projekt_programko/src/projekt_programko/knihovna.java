package projekt_programko;

import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;

public class knihovna {
	private Map<String, book> knihovna;
	
	public knihovna() {
		this.knihovna = SQL.load("tabulka");
	}
	
	 public void addKnihu(Scanner scanner) throws SQLException {
	        System.out.println("Aky typ knihy:");
	        System.out.println("1.Ucebnica");
	        System.out.println("2.Romany");
	        int bookVyber = pouzeCelaCisla(scanner);
	        
	        scanner.nextLine();

	        String nazev = "";
	        do {
	            System.out.println("Zadaj nazov: ");
	            nazev = scanner.nextLine();
	            if(nazev.matches(".*\\d.*")) {
	                System.out.println("Nazov cannot contain numbers. Please try again.");
	            }
	        } while(nazev.matches(".*\\d.*"));

	        System.out.println("Zadaj autora ak viac oddel ich bodkociarkov: ");
	        String[] authorsArray = scanner.nextLine().split(";");
	        List<String> autor = new ArrayList<>();
	        for (String author : authorsArray) {
	            autor.add(author.trim());
	        }

	        System.out.println("Zadaj rok vydania: ");
	        int rok_vydani = pouzeCelaCisla(scanner);

	        if (bookVyber == 1) {
	        	String typ="ucebnica";
	            System.out.println("Zadaj pre aku triedu to je: ");
	            int rocniKod = pouzeCelaCisla(scanner);
	            int zanerChoice = 6;
	            Zanr zanr = Zanr.values()[zanerChoice - 1];
	            knihovna.put(nazev, new book(nazev, autor, rok_vydani, typ, zanr, rocniKod, false));
	            System.out.println("Ucebnica pridana do kniznice: " + nazev);
	            System.out.println("Typ: Ucebnica");
	            System.out.println("Trieda cislo: " + rocniKod);
	            book book = datakniha(nazev);
				SQL.Upload(book);
	        } else if (bookVyber == 2) {
	        	String typ="roman";
	            System.out.println("Vyber si zaner:");
	            System.out.println("1. Historický");
	            System.out.println("2. Fantazie");
	            System.out.println("3. ROMANTIKA");
	            System.out.println("4. VOJNA");
	            System.out.println("5. THRILLER");
	            int zanerChoice = pouzeCelaCisla(scanner);
	           Zanr zanr = Zanr.values()[zanerChoice - 1]; 
	            knihovna.put(nazev, new book(nazev, autor, rok_vydani, typ, zanr, 0, false));
	            System.out.println("Roman pridany do kniznice: " + nazev);
	            System.out.println("Typ: Roman");
	            System.out.println("Zaner: " + zanr);
	            book book = datakniha(nazev);
				SQL.Upload(book);
	        } else {
	            System.out.println("Zly input.Kniha neni pridana do kniznice.");
	        }
	    }
	
	 public void updateKnihy(String nazev, Scanner scanner) throws SQLException {
	        book book = knihovna.get(nazev);
	        if (book != null) {
	            System.out.println("Aktualne udaje o knihe:");
	            System.out.println("Nazev: " + book.getNazev());
	            System.out.println("Autor: " + String.join("; ", book.getAutor()));
	            System.out.println("Rok vydani: " + book.getRok_vydani());
	            System.out.println("Pujceno: " + (book.getStav_vypujcky() ? "Ano" : "Ne"));
	            
	            scanner.nextLine();
	            // Prompt user for changes
	            System.out.println("Chces aktualizovat autora? (Y/N)");
	            String updateAutor = scanner.nextLine().trim();
	            if (updateAutor.equalsIgnoreCase("Y")) {
	                System.out.println("Zadaj noveho autora: ");
	                String newAutor = scanner.nextLine();
	                book.getAutor().clear();
	                book.getAutor().add(newAutor);
	            }
	            
	            System.out.println("Chces aktualizovat rok vydani? (Y/N)");
	            String updateRokVydani = scanner.nextLine().trim();
	            if (updateRokVydani.equalsIgnoreCase("Y")) {
	                System.out.println("Zadaj novy rok vydani: ");
	                int newRokVydani = Integer.parseInt(scanner.nextLine().trim());
	                book.setRok_vydani(newRokVydani);
	            }

	            
	            System.out.println("Chces aktualizovat stav vypujcky? (Y/N)");
	            String updateStavVypujcky = scanner.nextLine().trim();
	            if (updateStavVypujcky.equalsIgnoreCase("Y")) {
	                System.out.println("Je tato kniha aktualne vypujcena? (Y/N)");
	                boolean newStavVypujcky = scanner.nextLine().trim().equalsIgnoreCase("Y");
	                book.setStav_vypujcky(newStavVypujcky);
	            }

	            System.out.println("Kniha aktualizovana.");
	            SQL.Update(book);
	        } else {
	            System.out.println("Kniha nenajdena.");
	        }
	    }
		
	 public boolean smazaniknihy(String nazev) throws SQLException {
		 book book = datakniha(nazev);
		    if (book != null && knihovna.remove(nazev) != null) {
		        SQL.Delete(book);
		        return true;
		    }		
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
	            System.out.println("Pujceno: " + (book.getStav_vypujcky() ? "Ano" : "Ne"));
	            System.out.println();
	            System.out.println();
	        }
		 
	  }
	public book datakniha(String nazev) {
		
		 for (book book : knihovna.values()) {
		        if(book.getNazev().equals(nazev)) {
		            return book;
		        }
        }
		
		return null;
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
	            System.out.println("Pujceno: " + (book.getStav_vypujcky() ? "Ano" : "Ne"));
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
			if(book.getStav_vypujcky()) {
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
				writer.println("Nazev,Autor,Rok vydani Typ ,pujceno, zanr, rocnik id");
                writer.println(book.getNazev() + "," + String.join("; ", book.getAutor()) + "," + book.getRok_vydani() + ","+ (book.getTyp()) + "," +(book.getStav_vypujcky() ? "Yes"+ "," : "No" + "," +(book.getZanr()+"," + (book.getRocniKod()))));
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
	 
	public void vypujceni(String nazev) throws SQLException {
		book book = knihovna.get(nazev);
		 if (book !=null) {
			 if(!book.getStav_vypujcky()) {
				 book.setStav_vypujcky(true);
				 SQL.Update(book);
				 System.out.println("Kniha " + nazev + " byla vypujcena");
			 } else {
				 System.out.println("Kniha " + nazev + " jiz byla vypujcena!");
			 } 
			 } else {
				 System.out.println("Kniha " + nazev + " nebyla nalezena");
				 
			 }
	 	}
	public void vraceni(String nazev) throws SQLException {
	    book book = knihovna.get(nazev);
	    if (book != null) {
	        if (book.getStav_vypujcky()==true) {
	        	book.setStav_vypujcky(false);
	        	SQL.Update(book);
	            System.out.println("Kniha " + nazev + " byla vracena");
	        } else {
	            System.out.println("Kniha " + nazev + " jiz byla vracena!");
	        }
	    } else {
	        System.out.println("Kniha " + nazev + " nebyla nalezena");
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
	            System.out.println("Pujceno: " + (book.getStav_vypujcky() ? "Ano" : "Ne"));
	            System.out.println();
			}
		} else {
			System.out.println("Zadne knihy v knihovne");
		}
		
		
	}
	public boolean nahrajzSouboru(String nazov_souboru) throws SQLException {
	    BufferedReader in = null;
	    boolean success = true;
	    try {
	        in = new BufferedReader(new FileReader(nazov_souboru + ".txt"));
	        String line = in.readLine(); // Read and ignore header line
	        while ((line = in.readLine()) != null) {
	            String[] bookData = line.split(",");
	            if (bookData.length == 7) { // Ensure correct number of elements
	                String nazev = bookData[0];
	                List<String> autor = Arrays.asList(bookData[1].split(","));
	                int rok_vydani = Integer.parseInt(bookData[2]);
	                String typ = bookData[3];
	                boolean stav_vypujcky = bookData[4].equalsIgnoreCase("Yes");
	                if (typ.equalsIgnoreCase("roman")) {
	                    Zanr zanr = Zanr.valueOf(bookData[5]);
	                    knihovna.put(nazev, new book(nazev, autor, rok_vydani, typ, zanr, 0, stav_vypujcky));
	                    book book = datakniha(nazev);
	    				SQL.Upload(book);
	                } else if (typ.equalsIgnoreCase("ucebnica")) {
	                    int rocniKod = Integer.parseInt(bookData[6]);
	                    knihovna.put(nazev, new book(nazev, autor, rok_vydani, typ, null, rocniKod, stav_vypujcky));
	                    book book = datakniha(nazev);
						SQL.Upload(book);
	                }
	            } else {
	                System.err.println("Spatny format: " + nazov_souboru);
	                success = false;
	                break;
	            }
	        }
	    } catch (IOException | NumberFormatException e) {
	        System.err.println("Error neviem precitat soubor: " + nazov_souboru);
	        e.printStackTrace();
	        success = false;
	    } finally {
	        try {
	            if (in != null) {
	                in.close();
	            }
	        } catch (IOException e) {
	            System.err.println("Error pri zatvarani souboru: " + nazov_souboru);
	            e.printStackTrace();
	            success = false;
	        }
	    }
	    return success;
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
			System.out.println("Zadejte prosim cele cislo: ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}

	   
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

