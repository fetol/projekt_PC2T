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
		boolean run=true;
		while(run)
		{
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vlozeni nove knihy");
			System.out.println("2 .. uprava knihy");
			System.out.println("3 .. smazani knihy");
			System.out.println("4 .. oznaceni knihy jako vypujcene/vracene");
			System.out.println("5 .. vypis knih ");
			System.out.println("6 .. vyhledani knihy ");
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
					System.out.println("Zadejte nazov,autora,rok_vydania,typ_knihy");
					nazev=sc.next();
					
	                autor.add(sc.next());
					rok_vydani=App.pouzeCelaCisla(sc);
					typ=sc.next();
					if (typ.equals("novela") || typ.equals("ucebnice")) {
                        knihovna.addKnihu(nazev, autor, rok_vydani, typ, null, 0);
                    } else {
                        System.out.println("Zly typ knihy.Kniha nebude pridana");
                    }
					break;
				case 2:
					System.out.println("Zadejte nazov,autora,rok_vydania,typ_knihy,stav_vypujcky");
					nazev=sc.next();
	                autor2=sc.next();
					rok_vydani=App.pouzeCelaCisla(sc);
					typ=sc.next();
					System.out.println(" Pozicana/vracena (true or false): ");
				    stav_vypujcky = sc.hasNextBoolean();
				    if (stav_vypujcky) {
				        stav_vypujcky = sc.nextBoolean();
				    } else {
				        System.out.println("Invalid input. Please enter a valid boolean value.");
				    }
				    knihovna.updateKnihy(nazev,autor2,rok_vydani,stav_vypujcky);
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
					System.out.println("");
					
				case 5:
					 knihovna.listovaniKnih();
					break;
				case 6:
					
					break;
				case 7:
					
					break;
				case 8:
					
					break;
				case 9:
	
					break;
				case 10:
					System.out.println("Zadejte nazev knihy k ulozeni");
					nazev=sc.next();
					knihovna.ulozitknihudodsouboru(nazev);
					break;
				case 11:
					System.out.println("Zadejte nazev souboru k nacteni");
					if (knihovna.nacistknihudodzosouboru(sc.next()))
						System.out.println("kniha nactena");
					else
						System.out.println("knihu nebylo mozno nacist");
					break;				
				case 12:
					run=false;
					break;
			}
			
		}
	}
}
