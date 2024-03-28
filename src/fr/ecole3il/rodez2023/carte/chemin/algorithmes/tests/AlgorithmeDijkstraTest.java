package fr.ecole3il.rodez2023.carte.chemin.algorithmes.tests;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmeDijkstraTest {

    @Test
    void testTrouverChemin_GrapheVide() {
        Graphe<String> graphe = new Graphe<>();
        AlgorithmeDijkstra<String> algo = new AlgorithmeDijkstra<>();

        Noeud<String> depart = new Noeud<>("A");
        Noeud<String> arrivee = new Noeud<>("B");

        List<Noeud<String>> chemin = algo.trouverChemin(graphe, depart, arrivee);
        assertTrue(chemin.isEmpty());
    }

    @Test
    void testTrouverChemin_CheminSimple() {
        Graphe<String> graphe = new Graphe<>();
        Noeud<String> a = new Noeud<>("A");
        Noeud<String> b = new Noeud<>("B");
        Noeud<String> c = new Noeud<>("C");

        graphe.ajouterNoeud(a);
        graphe.ajouterNoeud(b);
        graphe.ajouterNoeud(c);

        graphe.ajouterArete(a, b, 2.0);
        graphe.ajouterArete(b, c, 3.0);

        AlgorithmeDijkstra<String> algo = new AlgorithmeDijkstra<>();
        List<Noeud<String>> chemin = algo.trouverChemin(graphe, a, c);

        assertEquals(3, chemin.size());
        assertEquals(a, chemin.get(0));
        assertEquals(b, chemin.get(1));
        assertEquals(c, chemin.get(2));
    }

    @Test
    void testTrouverChemin_PoidsNegatifs() {
        Graphe<String> graphe = new Graphe<>();
        Noeud<String> a = new Noeud<>("A");
        Noeud<String> b = new Noeud<>("B");
        Noeud<String> c = new Noeud<>("C");
        Noeud<String> d = new Noeud<>("D");

        graphe.ajouterNoeud(a);
        graphe.ajouterNoeud(b);
        graphe.ajouterNoeud(c);
        graphe.ajouterNoeud(d);

        graphe.ajouterArete(a, b, 2.0);
        graphe.ajouterArete(b, c, 3.0);
        graphe.ajouterArete(a, c, 6.0);
        graphe.ajouterArete(b, d, -5.0);

        AlgorithmeDijkstra<String> algo = new AlgorithmeDijkstra<>();
        List<Noeud<String>> chemin = algo.trouverChemin(graphe, a, d);

        assertEquals(3, chemin.size());
        assertEquals(a, chemin.get(0));
        assertEquals(b, chemin.get(1));
        assertEquals(d, chemin.get(2));
    }

    @Test
    void testTrouverChemin_PasDeCheminPossible() {
        Graphe<String> graphe = new Graphe<>();
        Noeud<String> a = new Noeud<>("A");
        Noeud<String> b = new Noeud<>("B");
        Noeud<String> c = new Noeud<>("C");

        graphe.ajouterNoeud(a);
        graphe.ajouterNoeud(b);
        graphe.ajouterNoeud(c);

        graphe.ajouterArete(a, b, 2.0);

        AlgorithmeDijkstra<String> algo = new AlgorithmeDijkstra<>();
        List<Noeud<String>> chemin = algo.trouverChemin(graphe, a, c);

        assertTrue(chemin.isEmpty());
    }

    @Test
    void testTrouverChemin_NoeudDepartEgalNoeudArrivee() {
        Graphe<String> graphe = new Graphe<>();
        Noeud<String> a = new Noeud<>("A");

        graphe.ajouterNoeud(a);

        AlgorithmeDijkstra<String> algo = new AlgorithmeDijkstra<>();
        List<Noeud<String>> chemin = algo.trouverChemin(graphe, a, a);

        assertEquals(1, chemin.size());
        assertEquals(a, chemin.get(0));
    }
}
        