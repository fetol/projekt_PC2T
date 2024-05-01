package projekt_programko;
import java.util.List;

enum Zanr {
	Historický,
    FANTAZIE,
    ROMANTIKA,
    VOJNA,
    THRILLER
}

public class book {
	private String nazev;
	private List <String> autor;
	private int rok_vydani;
	private boolean stav_vypujcky;
	private String typ;
	private Zanr zanr;
	private int rocniKod;

	    
	public book (String nazev, List <String> autor, int rok_vydani, String typ ,Zanr zanr,int rocniKod) {
		this.nazev= nazev;
		this.autor= autor;
		this.rok_vydani = rok_vydani;
		this.stav_vypujcky = false;
		this.typ = typ;
		this.zanr = zanr;
		this.rocniKod = rocniKod;
	}
	public String getNazev() {
		return nazev;
	}
	public Zanr getZanr() {
		return zanr;
	}
	public void setZaner(Zanr zanr) {
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
	public int getRocniKod() {
	    return rocniKod;
	}
	public void setRocniKod(int rocniKod) {
		this.rocniKod = rocniKod;
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