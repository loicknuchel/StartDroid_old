package com.lknuchel.sample.lists.model;

import java.util.ArrayList;
import java.util.List;

public class Livre {
    private String titre;
    private String auteur;
    private Float prix;

    public Livre(String titre, String auteur) {
	this.titre = titre;
	this.auteur = auteur;
	this.prix = -1f;
    }

    public Livre(String titre, String auteur, Float prix) {
	this.titre = titre;
	this.auteur = auteur;
	this.prix = prix;
    }

    public String getTitre() {
	return titre;
    }

    public void setTitre(String titre) {
	this.titre = titre;
    }

    public String getAuteur() {
	return auteur;
    }

    public void setAuteur(String auteur) {
	this.auteur = auteur;
    }

    public void setPrix(Float prix) {
	this.prix = prix;
    }

    public Float getPrix() {
	return prix;
    }

    public static List<Livre> generateLivres(int nb) {
	List<Livre> list = new ArrayList<Livre>();
	List<Livre> list2 = new ArrayList<Livre>();

	list = generate10Livres();
	if(nb > 10){nb = 10;}
	for(int i=0; i<nb; ++i){
	    list2.add(list.get(i));
	}

	return list2;
    }

    public static List<Livre> generate5Livres() {
	List<Livre> list = new ArrayList<Livre>();

	list.add(new Livre("Mon premier livre", "Loic Knuchel"));
	list.add(new Livre("HTML5", "Jonathan Verrecchia"));
	list.add(new Livre("Les diables du ciel", "William-C Dietz"));
	list.add(new Livre("L'art du développement Android", "Mark Murphy"));
	list.add(new Livre("Le seuil des ténèbres", "Karen Chance"));

	return list;
    }

    public static List<Livre> generate10Livres() {
	List<Livre> list = new ArrayList<Livre>();

	list.add(new Livre("Mon premier livre", "Loic Knuchel"));
	list.add(new Livre("Un livre", "Hervé Tullet", 10.36f));
	list.add(new Livre("Le Livre des contes perdus, tome 1", "Tolkien",
		6.27f));
	list.add(new Livre("HTML5", "Jonathan Verrecchia"));
	list.add(new Livre("Le Livre du Voyage", "Bernard Werber", 3.32f));
	list.add(new Livre("Le seuil des ténèbres", "Karen Chance"));
	list.add(new Livre(
		"Le livre des coïncidences - Vivre à l'écoute des signes que le destin nous envoie",
		"Dr Deepak Chopra", 6.36f));
	list.add(new Livre("Le livre des morts",
		"Glenn Cooper et Carine Chichereau", 7.50f));
	list.add(new Livre("Les diables du ciel", "William-C Dietz"));
	list.add(new Livre("L'art du développement Android", "Mark Murphy"));

	return list;
    }
}