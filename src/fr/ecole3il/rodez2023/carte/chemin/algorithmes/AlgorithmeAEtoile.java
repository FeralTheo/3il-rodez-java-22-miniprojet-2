package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

/**
 * La classe AlgorithmeAEtoile implémente l'interface AlgorithmeChemin et
 * utilise l'algorithme A* pour trouver un chemin entre un nœud de départ et un
 * nœud d'arrivée dans un graphe donné.
 *
 * @param <E> Le type générique des valeurs stockées dans les nœuds du graphe.
 */
public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

	@Override
	public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
		// Initialisation
		PriorityQueue<Noeud<E>> fileAttente = new PriorityQueue<>((n1, n2) -> {
			double diff = getCoutTotal(n1, arrivee, graphe) - getCoutTotal(n2, arrivee, graphe);
			if (diff == 0)
				return 0;
			return diff < 0 ? -1 : 1;
		});
		Set<Noeud<E>> explored = new HashSet<>();
		Map<Noeud<E>, Double> coutTotal = new HashMap<>();
		Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();

		// Initialisation des coûts
		for (Noeud<E> noeud : graphe.getNoeuds()) {
			coutTotal.put(noeud, Double.POSITIVE_INFINITY);
			predecesseurs.put(noeud, null);
		}
		coutTotal.put(depart, heuristique(depart, arrivee));

		fileAttente.offer(depart);

		// Boucle principale
		while (!fileAttente.isEmpty()) {
			Noeud<E> noeudActuel = fileAttente.poll();

			if (noeudActuel.equals(arrivee)) {
				// Reconstruction du chemin
				List<Noeud<E>> chemin = reconstruireChemin(predecesseurs, arrivee);
				return chemin;
			}

			if (!explored.contains(noeudActuel)) {
				explored.add(noeudActuel);

				// Mise à jour des coûts
				for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
					if (!explored.contains(voisin)) {
						double coutActuel = coutTotal.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
						double nouveauCoutTotal = coutActuel + heuristique(voisin, arrivee);

						if (nouveauCoutTotal < coutTotal.get(voisin)) {
							coutTotal.put(voisin, nouveauCoutTotal);
							predecesseurs.put(voisin, noeudActuel);
							fileAttente.offer(voisin);
						}
					}
				}
			}
		}

		// Aucun chemin trouvé
		return null;
	}

	private double heuristique(Noeud<E> noeud, Noeud<E> arrivee) {
		Case caseNoeud = (Case) noeud.getValeur();
		Case caseArrivee = (Case) arrivee.getValeur();
		int dx = Math.abs(caseNoeud.getX() - caseArrivee.getX());
		int dy = Math.abs(caseNoeud.getY() - caseArrivee.getY());
		return Math.sqrt(dx * dx + dy * dy);
	}

	private double getCoutTotal(Noeud<E> noeud, Noeud<E> arrivee, Graphe<E> graphe) {
		double coutActuel = 0.0;
		Noeud<E> noeudCourant = noeud;
		while (noeudCourant != null) {
			if (graphe.getCoutArete(noeudCourant, noeud) != Double.POSITIVE_INFINITY) {
				coutActuel += graphe.getCoutArete(noeudCourant, noeud);
				noeudCourant = noeud;
			} else {
				break;
			}
		}
		return coutActuel + heuristique(noeud, arrivee);
	}

	private List<Noeud<E>> reconstruireChemin(Map<Noeud<E>, Noeud<E>> predecesseurs, Noeud<E> arrivee) {
		List<Noeud<E>> chemin = new ArrayList<>();
		Noeud<E> noeud = arrivee;
		while (noeud != null) {
			chemin.add(0, noeud);
			noeud = predecesseurs.get(noeud);
		}
		return chemin;
	}
}