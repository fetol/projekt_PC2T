package projekt_programko;

import java.util.Arrays;
import java.util.List;

public class App {

	public static void main(String[] args) {
		knihovna knihovna = new knihovna();

        // Přidání nové knihy
        String title = "Introduction to Java";
        List<String> authors = Arrays.asList("John Doe");
       
        int year = 2022;
        
        String type = "ucebnice"; // Typ knihy
        knihovna.addKnihu(title, authors, year, type);
        
        

        
        knihovna.listovaniKnih();
	}
}
