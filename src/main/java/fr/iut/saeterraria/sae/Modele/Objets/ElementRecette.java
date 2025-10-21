package fr.iut.saeterraria.sae.Modele.Objets;

public class ElementRecette {
    private int idItem;
    private String nom;
    private int quantite;

    public ElementRecette(int idItem,String nom, int quantite) {
        this.idItem = idItem;
        this.nom = nom;
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getIdItem() {
        return this.idItem;
    }

    public String getNom() {
        return this.nom;
    }
}
