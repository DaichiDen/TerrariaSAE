package fr.iut.saeterraria.sae.Modele.Objets;

public class ElementRecette {
    private int idItem;
    private int quantite;

    public ElementRecette(int idItem, int quantite) {
        this.idItem = idItem;
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getIdItem() {
        return this.idItem;
    }
}
