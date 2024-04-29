package projekt_programko;
import java.util.List;
public class book {
	private String nazev;
	private List <String> autor;
	private int rok_vydani;
	private boolean stav_vypujcky;
	
	public book (String nazev, List <String> autor, int rok_vydani) {
		this.nazev= nazev;
		this.autor= autor;
		this.rok_vydani = rok_vydani;
		this.stav_vypujcky = false
	}
	public String getNazev() {
		return nazev;
	}
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}
	public List<String> getAutor(){
		return autor;
	}
	public void setAutor(List<String>autor) {
		this.autor = autor;
	}
	public int getRok_vydani() {
		return rok_vydani;
	}
	public int setRok_vydaní() {
		this.rok_vydani = rok_vydani;
	}
	public boolean stav_vypujcky() {
		return stav_vypujcky;
	}
	public void set stav_vypujcky(boolean pujceno) {
		if(!pujceno) {
			this.stav_vypujcky = pujceno;
		}
		
			
	}
}