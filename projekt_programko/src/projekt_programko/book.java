package projekt_programko;
import java.util.List;
public class book {
	private String nazev;
	private String zanr;
	private List <String> autor;
	private int rok_vydani;
	private boolean stav_vypujcky;
	private String typ;
	
	public book (String nazev, List <String> autor, int rok_vydani, String typ) {
		this.nazev= nazev;
		this.autor= autor;
		this.rok_vydani = rok_vydani;
		this.stav_vypujcky = false;
		this.typ = typ;
	}
	public String getNazev() {
		return nazev;
	}
	public String getZanr() {
		return zanr;
	}
	public void setZaner(String zanr) {
		this.zanr = zanr;
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
	public void setRok_vydani(int rok_vydani) {
		this.rok_vydani = rok_vydani;
	}
	public boolean stav_vypujcky() {
		return stav_vypujcky;
	}
	public void set_stav_vypujcky(boolean pujceno) {
		if(!pujceno) {
			this.stav_vypujcky = pujceno;
		}
	}
		public String getTyp() {
	        return typ;
	    }

	    public void setType(String typ) {
	        this.typ = typ;
	        }
}