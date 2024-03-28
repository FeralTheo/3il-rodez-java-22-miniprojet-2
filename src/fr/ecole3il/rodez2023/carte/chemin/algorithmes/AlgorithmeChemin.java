package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import java.util.List;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

/**
 * L'interface AlgorithmeChemin définit un contrat pour les classes qui
 * implémentent des algorithmes de recherche de chemin dans un graphe.
 *
 * @param <E> Le type générique des valeurs stockées dans les nœuds du graphe.
 */
public interface AlgorithmeChemin<E> {
	/**
	 * Trouve un chemin entre un nœud de départ et un nœud d'arrivée dans un graphe
	 * donné.
	 *
	 * @param graphe  Le graphe dans lequel la recherche doit être effectuée.
	 * @param depart  Le nœud de départ.
	 * @param arrivee Le nœud d'arrivée.
	 * @return Une liste de nœuds représentant le chemin trouvé entre le nœud de
	 *         départ et le nœud d'arrivée dans le graphe.
	 */
	List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
}