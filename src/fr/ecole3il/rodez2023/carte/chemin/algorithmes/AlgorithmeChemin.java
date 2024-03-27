package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import java.util.List;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

public interface AlgorithmeChemin<E> {
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
}