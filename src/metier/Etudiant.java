package metier;

import java.util.List;

enum ETATCIVIL{
	Mr,
	Mme;
}
public class Etudiant {
	private String nom,prenom,annee,semestre,comment;
	private ETATCIVIL etatcivil;
	private List<NiveauPreference> rn;
	
	public Etudiant(String nom,String prenom,String annee,String semestre,String comment,ETATCIVIL etatcivil,List<NiveauPreference> rn) {
		this.nom=nom;
		this.prenom=prenom;
		this.annee=annee;
		this.semestre=semestre;
		this.comment=comment;
		this.etatcivil=etatcivil;
		this.rn=rn;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public ETATCIVIL getEtatcivil() {
		return etatcivil;
	}
	public void setEtatcivil(ETATCIVIL etatcivil) {
		this.etatcivil = etatcivil;
	}
	public List<NiveauPreference> getRn() {
		return rn;
	}
	public void setRn(List<NiveauPreference> rn) {
		this.rn = rn;
	}
	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", annee=" + annee + ", semestre=" + semestre
				+ ", comment=" + comment + ", etatcivil=" + etatcivil + ", rn=" + rn + "]";
	}
	public static ETATCIVIL strToEtat(String s) {
		if(s.equals("Mr")) {
			return ETATCIVIL.Mr;
		}else {
			return ETATCIVIL.Mme;
		}
	}
	
}
