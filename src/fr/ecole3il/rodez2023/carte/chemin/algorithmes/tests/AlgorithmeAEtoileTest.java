package fr.ecole3il.rodez2023.carte.chemin.algorithmes.tests;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeAEtoile;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmeAEtoileTest {

    @Test
    void testTrouverChemin_GrapheVide() {
        Graphe<Case> graphe = new Graphe<>();
        AlgorithmeAEtoile<Case> algo = new AlgorithmeAEtoile<>();

        Noeud<Case> depart = new Noeud<>(new Case(null,0, 0));
        Noeud<Case> arrivee = new Noeud<>(new Case(null,1, 1));

        List<Noeud<Case>> chemin = algo.trouverChemin(graphe, depart, arrivee);
        assertNull(chemin);
    }

    @Test
    void testTrouverChemin_CheminSimple() {
        Graphe<Case> graphe = new Graphe<>();
        Noeud<Case> a = new Noeud<>(new Case(null,0, 0));
        Noeud<Case> b = new Noeud<>(new Case(null,1, 1));
        Noeud<Case> c = new Noeud<>(new Case(null,2, 2));

        graphe.ajouterNoeud(a);
        graphe.ajouterNoeud(b);
        graphe.ajouterNoeud(c);

        graphe.ajouterArete(a, b, 1.0);
        graphe.ajouterArete(b, c, 1.0);

        AlgorithmeAEtoile<Case> algo = new AlgorithmeAEtoile<>();
        List<Noeud<Case>> chemin = algo.trouverChemin(graphe, a, c);

        assertEquals(3, chemin.size());
        assertEquals(a, chemin.get(0));
        assertEquals(b, chemin.get(1));
        assertEquals(c, chemin.get(2));
    }

    @Test
    void testTrouverChemin_PasDeCheminPossible() {
        Graphe<Case> graphe = new Graphe<>();
        Noeud<Case> a = new Noeud<>(new Case(null,0, 0));
        Noeud<Case> b = new Noeud<>(new Case(null,1, 1));
        Noeud<Case> c = new Noeud<>(new Case(null,2, 2));

        graphe.ajouterNoeud(a);
        graphe.ajouterNoeud(b);
        graphe.ajouterNoeud(c);

        graphe.ajouterArete(a, b, 1.0);

        AlgorithmeAEtoile<Case> algo = new AlgorithmeAEtoile<>();
        List<Noeud<Case>> chemin = algo.trouverChemin(graphe, a, c);

        assertNull(chemin);
    }

    @Test
    void testTrouverChemin_NoeudDepartEgalNoeudArrivee() {
        Graphe<Case> graphe = new Graphe<>();
        Noeud<Case> a = new Noeud<>(new Case(null,0, 0));

        graphe.ajouterNoeud(a);

        AlgorithmeAEtoile<Case> algo = new AlgorithmeAEtoile<>();
        List<Noeud<Case>> chemin = algo.trouverChemin(graphe, a, a);

        assertEquals(1, chemin.size());
        assertEquals(a, chemin.get(0));
    }
    @Test
    void testTrouverChemin_CheminAvecObstacles() {
        Graphe<Case> graphe = new Graphe<>();
        Noeud<Case> a = new Noeud<>(new Case(null,0, 0));
        Noeud<Case> b = new Noeud<>(new Case(null,1, 1));
        Noeud<Case> c = new Noeud<>(new Case(null,2, 0));
        Noeud<Case> d = new Noeud<>(new Case(null,3, 1));
        Noeud<Case> e = new Noeud<>(new Case(null,4, 0));

        graphe.ajouterNoeud(a);
        graphe.ajouterNoeud(b);
        graphe.ajouterNoeud(c);
        graphe.ajouterNoeud(d);
        graphe.ajouterNoeud(e);

        graphe.ajouterArete(a, b, 1.0);
        graphe.ajouterArete(b, c, 1.0);
        graphe.ajouterArete(c, d, 1.0);
        graphe.ajouterArete(d, e, 1.0);

        AlgorithmeAEtoile<Case> algo = new AlgorithmeAEtoile<>();
        List<Noeud<Case>> chemin = algo.trouverChemin(graphe, a, e);

        assertEquals(5, chemin.size());
        assertEquals(a, chemin.get(0));
        assertEquals(b, chemin.get(1));
        assertEquals(c, chemin.get(2));
        assertEquals(d, chemin.get(3));
        assertEquals(e, chemin.get(4));
    }

    @Test
    void testTrouverChemin_CheminAvecPoidsDifferents() {
        Graphe<Case> graphe = new Graphe<>();
        Noeud<Case> a = new Noeud<>(new Case(null,0, 0));
        Noeud<Case> b = new Noeud<>(new Case(null,1, 1));
        Noeud<Case> c = new Noeud<>(new Case(null,2, 0));
        Noeud<Case> d = new Noeud<>(new Case(null, 3, 1));
        Noeud<Case> e = new Noeud<>(new Case(null, 4, 0));

        graphe.ajouterNoeud(a);
        graphe.ajouterNoeud(b);
        graphe.ajouterNoeud(c);
        graphe.ajouterNoeud(d);
        graphe.ajouterNoeud(e);

        graphe.ajouterArete(a, b, 1.0);
        graphe.ajouterArete(b, c, 2.0);
        graphe.ajouterArete(c, d, 1.0);
        graphe.ajouterArete(d, e, 3.0);

        AlgorithmeAEtoile<Case> algo = new AlgorithmeAEtoile<>();
        List<Noeud<Case>> chemin = algo.trouverChemin(graphe, a, e);

        assertEquals(5, chemin.size());
        assertEquals(a, chemin.get(0));
        assertEquals(b, chemin.get(1));
        assertEquals(c, chemin.get(2));
        assertEquals(d, chemin.get(3));
        assertEquals(e, chemin.get(4));
    }
}