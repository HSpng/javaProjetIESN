package ProjetJava;

public class DateException extends Exception{
	private int jour, mois, annee;
	
	public DateException(int jour, int mois, int annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
		
	}
	
	public String toString() {
		String phrase = "";
		if(jour < 1 || jour > 31) {
			phrase =  "La valeur du jour '"+jour+"' n'est pas valide";
		}else if(mois < 1 || mois > 12) {
			phrase =  "La valeur du mois '"+mois+"' n'est pas valide";
		}else if(annee < 2000 || annee > 2100) {
			phrase =  "La valeur de l'annee '"+annee+"' n'est pas valide";
		}
		return phrase;
	}
}
