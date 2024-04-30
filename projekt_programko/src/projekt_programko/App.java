package projekt_programko;

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
					System.out.println("Zadejte jmeno studenta, rok narozeni");

					break;
				case 2:
					System.out.println("Zadejte jmeno a prumer studenta");
					
					break;
				case 3:
					System.out.println("Zadejte jmeno studenta");
					
					break;
				case 4:
					System.out.println("Zadejte jmeno studenta k odstraneni");
					
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
	
					break;
				case 11:
					
					break;
					
				case 12:
					run=false;
					break;
			}
			
		}
	}
}
