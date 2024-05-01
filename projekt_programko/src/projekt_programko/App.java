package projekt_programko;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	
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
	
	public static float pouzeCisla(Scanner sc) 
	{
		float cislo = 0;
		try
		{
			cislo = sc.nextFloat();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cislo ");
			sc.nextLine();
			cislo = pouzeCisla(sc);
		}
		return cislo;
	}

	public static void main(String[] args) {

		
		knihovna knihovna = new knihovna();
		Scanner sc=new Scanner(System.in);
		String nazev;
		List<String> autor=new ArrayList<>();
		String autor2;
		int rok_vydani;
		String typ;
		boolean stav_vypujcky;
		String nazev_souboru;
		autor.clear();
		Zanr zanr;
		boolean run=true;
        //DatabaseConnection.nacitatzDB();
		while(run)
		
		{
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vlozeni nove knihy");
			System.out.println("2 .. uprava knihy");
			System.out.println("3 .. smazani knihy");
			System.out.println("4 .. oznaceni knihy jako vypujcene/vracene");
			System.out.println("5 .. vypis knih ");
			System.out.println("6 .. vyhledani knihy podla nazvu ");
			System.out.println("7 .. vypis vsech knih daneho autora");
			System.out.println("8 .. vypis vsech knih, ktere patri do konkretneho zanru");
			System.out.println("9 .. vypis vsech vypujcenych knih s informaciou ci roman alebo ucebnica");
			System.out.println("10 ..ulozenie informace o vybrane knihe do souboru");
			System.out.println("11 ..nacteni vsech informaci o dane knize ze souboru");
			System.out.println("12 ..ukonceni aplikace");
			
			int volba = pouzeCelaCisla(sc);
			switch(volba)
			{
				case 1:
					knihovna.addKnihu(sc);
					break;
				case 2:
					System.out.println("Zadejte nazev knihy k editovaniu");
					nazev=sc.next();
				    knihovna.updateKnihy(nazev, sc);
				    break;
				case 3:
					System.out.println("Zadejte nazev knihy k odstraneni");
					nazev=sc.next();
					if (knihovna.smazaniknihy(nazev))
						System.out.println(nazev + " odstranen ");
					else
						System.out.println(nazev + " neni v databazi ");				
					break;
				case 4:
					System.out.println("Chces vratit alebo vypujcit:");
			        System.out.println("1.Vratit");
			        System.out.println("2.Vypujcit");
			        int Vyber = pouzeCelaCisla(sc);
					 if ( Vyber == 1) {
						 System.out.println("Zadejte nazev knihy");
						 	nazev=sc.next();
				            knihovna.vraceni(nazev);
				        } else if (Vyber == 2) {
				        	System.out.println("Zadejte nazev knihy");
						 	nazev=sc.next();
				            knihovna.vypujceni(nazev);	
				        }
					break;
				case 5:
					 knihovna.listovaniKnih();
					break;
				case 6:
					System.out.println("Zadaj nazov knihy?");
					nazev=sc.next();
					knihovna.hledani_knihy(nazev);
					break;
				case 7:
					System.out.println("Zadaj nazov autora?");
					autor2=sc.next();
					knihovna.razeniKnihyPodleAutoraChronologicky(autor2);
					break;
				case 8:
					System.out.println("Vyber si zaner:");
		            System.out.println("1. Historický");
		            System.out.println("2. Fantazie");
		            System.out.println("3. ROMANTIKA");
		            System.out.println("4. VOJNA");
		            System.out.println("5. THRILLER");
		            int zanerChoice = pouzeCelaCisla(sc);
		            zanr = Zanr.values()[zanerChoice - 1];
		            knihovna.podlezanru(zanr);
					break;
				case 9:
					knihovna.listovaniPujcenychKnih(); //nejde lebo nejde pujcovanie
					break;
				case 10:
					System.out.println("Zadejte nazev knihy k ulozeni");
					nazev=sc.next();
					knihovna.ulozitknihudodsouboru(nazev);
					break;
				case 11:
					System.out.println("Zadejte nazev souboru k nacteni");
					if (knihovna.loadBookFromFile(sc.next()))
						System.out.println("Databaze nactena");
					else
						System.out.println("Databazi nebylo mozno nacist");
					break;			
				case 12:
					run=false;
					break;
			}
	    }
	
	}
}