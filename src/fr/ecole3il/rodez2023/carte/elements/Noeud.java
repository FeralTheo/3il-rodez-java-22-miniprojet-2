package fr.ecole3il.rodez2023.carte.elements;

import java.util.List;

public class Noeud<E> {
	
	private E valeur;
	private List<Noeud<E>> voisin;

	public Noeud(E valeur){
		super();
		this.valeur = valeur;
		this.voisin = voisin;
	}

	public E getValeur() {
		return valeur;
	}
	
	public List<Noeud<E>> getVoisins(){
		return voisin;
	}
	
	public void ajouterVoisin(Noeud<E> voisin) {
		this.voisin.add(voisin);
	}
}