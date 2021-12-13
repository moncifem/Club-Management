package metier;


enum NIVEAU{
	Debutant,
	Intermediare,
	Avance;
}
public class NiveauPreference {
	private NIVEAU niveau;
	private Preference preference;
	
	public NiveauPreference(NIVEAU niveau,Preference preference) {
		this.niveau=niveau;
		this.preference=preference;
	}
	public static NIVEAU strToNiveau(String s) {
		if(s.equals("débutant")) {
			return NIVEAU.Debutant;
		}else if(s.equals("intermediaire")){
			return NIVEAU.Intermediare;
		}else {
			return NIVEAU.Avance;
		}
	}
	@Override
	public String toString() {
		return "NiveauPreference [niveau=" + niveau + ", preference=" + preference + "]";
	}
	
}
