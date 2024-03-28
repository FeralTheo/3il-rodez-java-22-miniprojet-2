package fr.ecole3il.rodez2023.carte.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Noeud représente un nœud générique dans une structure de graphe. Un
 * nœud possède une valeur de type générique E et une liste de nœuds voisins,
 * également de type E.
 *
 * @param <E> Le type générique des valeurs stockées dans les nœuds.
 */
public class Noeud<E> {

	private E valeur;
	private List<Noeud<E>> voisin;

	/**
	 * Construit un nouvel objet Noeud avec la valeur spécifiée et initialise la
	 * liste de voisins.
	 *
	 * @param valeur La valeur du nœud.
	 */
	public Noeud(E valeur) {
		super();
		this.valeur = valeur;
		this.voisin = new ArrayList<>();
	}

	/**
	 * Retourne la valeur du nœud.
	 *
	 * @return La valeur du nœud.
	 */
	public E getValeur() {
		return valeur;
	}

	/**
	 * Retourne la liste des nœuds voisins.
	 *
	 * @return La liste des nœuds voisins.
	 */
	public List<Noeud<E>> getVoisins() {
		return voisin;
	}

	/**
	 * Ajoute un nœud voisin à la liste des voisins du nœud actuel.
	 *
	 * @param voisin Le nœud voisin à ajouter.
	 */
	public void ajouterVoisin(Noeud<E> voisin) {
		this.voisin.add(voisin);
	}
}